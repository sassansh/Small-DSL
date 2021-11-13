package ast;

import org.antlr.v4.runtime.tree.TerminalNode;
import java.io.PrintWriter;
import java.util.*;

public class YouAnimateEvaluator implements YouAnimateVisitor<Void> {
    private final PrintWriter writer;
    private static final Map<String, Statement> symbolTable = new HashMap<>();
    private static final Map<String, AnimationDef> animationDefTable = new HashMap<>();
    private static final Map<String, Integer> animationDefTimeKeeper = new HashMap<>();
    private static final Map<String, Integer> animationDefDurationTable = new HashMap<>();
    private static final Map<String, ArrayList<Integer>> objectBusyTimes = new HashMap<>();
    private static final Map<String, ArrayList<String>> groupElements = new HashMap<>();
    private static final String tabLevel0 = "\t\t\t\t";
    private static final String tabLevel1 = "\t\t\t\t\t";
    private static final String tabLevel2 = "\t\t\t\t\t\t";
    private static final String tabLevel3 = "\t\t\t\t\t\t\t";
    public static int loopIterator = 0; // tracks which iteration of the loop
    public static int loopCounter = 0; // tracks variable i value in the loop

    public YouAnimateEvaluator(PrintWriter p) {
        this.writer = p;
    }

    public static Map<String, Statement> getSymbolTable() {
        return symbolTable;
    }

    private int animCallNumber = 1;

    @Override
    public Void visit(ShapeDec s) {
        storeVariable(s, s.getShapeName());
        objectBusyTimes.put(s.getShapeName(), new ArrayList<>());

        String geometryName = s.getShapeName() + "Geometry";
        switch (s.getShape()) {
            case "circle":
                writer.println(tabLevel0 + "const " + geometryName + " = new THREE.CircleGeometry( " +
                        s.getShapeDimensions().getRadius() + ", 32 );");
                createThreeJsShape(s, geometryName);
                break;
            case "rectangle":
                writer.println(tabLevel0 + "const " + geometryName + " = new THREE.BoxGeometry( "
                        + s.getShapeDimensions().getWidth() + ", " + s.getShapeDimensions().getHeight() + ");");
                createThreeJsShape(s, geometryName);
                break;
            case "square":
                writer.println(tabLevel0 + "const " + geometryName + " = new THREE.BoxGeometry( "
                        + s.getShapeDimensions().getHeight() + ", " + s.getShapeDimensions().getHeight() + ");");
                createThreeJsShape(s, geometryName);
                break;
            case "sphere":
                writer.println(tabLevel0 + "const " + geometryName + " = new THREE.SphereGeometry( "
                        + s.getShapeDimensions().getRadius() + ", 64, 32 );");
                createThreeJsShape(s, geometryName);
                break;
            case "cone":
                writer.println(tabLevel0 + "const " + geometryName + " = new THREE.ConeGeometry( "
                        + s.getShapeDimensions().getRadius() + ", " + s.getShapeDimensions().getHeight() + ", 32 );");
                createThreeJsShape(s, geometryName);
                break;
            case "triangle":
                writer.println(tabLevel0 + "const " + geometryName + " = new THREE.BufferGeometry();");
                writer.println(tabLevel0 + "const " + geometryName + "Vertices = new Float32Array ( [\n" +
                        tabLevel1 + "-" + s.getShapeDimensions().getBase()/2 + ", -" + s.getShapeDimensions().getBase()/2 + ",  0,\n" +
                        tabLevel1 + s.getShapeDimensions().getBase()/2 + ", -" + s.getShapeDimensions().getBase()/2 + ",  0,\n" +
                        tabLevel1 + "0,  " + s.getShapeDimensions().getHeight() + ",  0,\n" +
                        tabLevel0 + "]);");
                writer.println(tabLevel0 + geometryName + ".setAttribute( 'position', new THREE.BufferAttribute( " +
                        geometryName + "Vertices, 3 ) );");
                createThreeJsShape(s, geometryName);
                break;
            case "cube":
                writer.println(tabLevel0 + "const " + geometryName + " = new THREE.BoxGeometry( "
                        + s.getShapeDimensions().getWidth() + ", " + s.getShapeDimensions().getHeight() + ", " +
                        s.getShapeDimensions().getDepth() + ");");
                createThreeJsShape(s, geometryName);
                break;
            default:
                break;
        }
        return null;
    }

    @Override
    public Void visit(Program p) {
        writer.println(p.getStart());
        p.getCanvasColour().accept(this);
        for (Statement s : p.getStatements()) {
                s.accept(this);
        }
        writer.println(p.getEnd());
        return null;
    }

    @Override
    public Void visit(CanvasColour cc) {
        writer.println(tabLevel0 + "scene.background = new THREE.Color('" + ColourPicker.getColour(cc.getColour()) + "');");
        return null;
    }

    @Override
    public Void visit(TextDec td) {
        storeVariable(td, td.getTextName());
        objectBusyTimes.put(td.getTextName(), new ArrayList<>());

        String loaderName = td.getTextName() + "Loader";
        String geometryName = td.getTextName() + "Geometry";
        String materialName = td.getTextName() + "Material";
        String meshName = td.getTextName() + "Mesh";
        writer.println(tabLevel0 + "const " + loaderName + " = new THREE.FontLoader();\n" +
                tabLevel0 + "let " + meshName + " = new THREE.Mesh();\n" +
                tabLevel0 + "let " + meshName + "_ready = false;");
        String fontSrc = "https://threejs.org/examples/fonts/helvetiker_regular.typeface.json";
        writer.println(tabLevel0 + loaderName + ".load( '" + fontSrc + "', function ( font ) {\n" +
                tabLevel1 + "const " + geometryName + " = new THREE.TextGeometry( " + td.getText() + ", {\n" +
                tabLevel2 + "font: font,\n" +
                tabLevel2 + "size: " + td.getFontSize() / 100.0 + ",\n" +
                tabLevel2 + "height: 0.05,\n" +
                tabLevel2 + "curveSegments: 12,\n" +
                tabLevel2 + "bevelEnabled: false,\n" +
                tabLevel1 + "} );\n" +
                tabLevel1 + geometryName + ".center();\n" +
                tabLevel1 + "const " + materialName + " = new THREE.MeshBasicMaterial( { color: '" + ColourPicker.getColour(td.getColour()) + "' });\n" +
                tabLevel1 + meshName + " = new THREE.Mesh( " + geometryName + ", " + materialName + " );\n" +
                tabLevel1 + meshName + "_ready = true;\n" +
                tabLevel0 + "} );");
        return null;
    }

    @Override
    public Void visit(XyCord coord) {
        return null;
    }

    @Override
    public Void visit(Move m) {
        double distanceX = (m.getEndPosition().getXCord() - m.getStartPosition().getXCord());
        double distanceY = (m.getEndPosition().getYCord() - m.getStartPosition().getYCord());
        String funcName = "move" + loopIterator + "_" + m.getObjName() + "_" + animCallNumber;
        loopIterator += 1;
        String timestamp = setLoopTimestamp(m.getTimestamp());
        rememberObjectIsBusy(m, timestamp);
        String firstCompX = (distanceX >= 0) ? "<": ">";
        String firstCompY = (distanceY >= 0) ? "<": ">";
        String variableName = lookupVariableName(m.getObjName());
        writer.println(tabLevel0 + "let " + funcName + "_started" + " = false;\n" +
                tabLevel0 + "let " + funcName + "_finished" + " = false;\n" +
                tabLevel0 + "let " + funcName + "_clock" + " = new THREE.Clock();");
        writer.println(tabLevel0 + "const " + funcName + " = function () {\n" +
                tabLevel1 + "requestAnimationFrame( " + funcName + " );\n" +
                tabLevel1 + "let speedX = " + distanceX / m.getDuration() + ";\n" +
                tabLevel1 + "let speedY = " + distanceY / m.getDuration() + ";\n" +
                tabLevel1 + "if (" + funcName + "_finished" + ") return;\n" +
                tabLevel1 + "let delta = " + funcName + "_clock" +".getDelta();\n" +
                tabLevel1 + "let timeDifference = ((new Date() - time)/1000);\n" +
                tabLevel1 + "if (timeDifference < " + timestamp + ") return;\n" +
                tabLevel1 + "if (" + variableName + "_ready && !" + funcName + "_started" + ") {\n" +
                tabLevel2 + variableName + ".position.x = " + m.getStartPosition().getXCord() + ";\n" +
                tabLevel2 + variableName + ".position.y = " + m.getStartPosition().getYCord() + ";\n" +
                tabLevel2 + variableName + ".position.z = " + animCallNumber * 5 + ";\n" +
                tabLevel2 + funcName + "_started" + "= true;\n" +
                tabLevel2 + "scene.add( " + variableName + " );\n" +
                tabLevel1 + "}\n" +

                tabLevel1 + "if (" + variableName + ".position.x " + firstCompX + m.getEndPosition().getXCord() + ") " +
                "{\n" +
                tabLevel2 + variableName + ".position.x += speedX * delta;\n" +
                tabLevel1 + "}\n" +
                tabLevel1 + "if (" + variableName + ".position.y " + firstCompY + m.getEndPosition().getYCord() + ") " +
                "{\n" +
                tabLevel2 + variableName + ".position.y += speedY * delta;\n" +
                tabLevel1 + "}\n" +
                tabLevel1 + "if (timeDifference > " + m.getDuration() + " + " + timestamp + " && !" + funcName +
                "_finished" + ") {\n" +
                tabLevel2 + "scene.remove(" + variableName + ");\n" +
                tabLevel2 + funcName + "_finished" + " = true;\n" +
                tabLevel1 + "}\n" +
                tabLevel1 + "renderer.render(scene, camera);\n" +
                tabLevel0 + "};\n" +
                tabLevel0  + funcName + "();");
        animCallNumber++;
        return null;
    }

    @Override
    public Void visit(Stay s) {
        String funcName = "stay" + loopIterator + "_" + s.getObjName() + "_" + animCallNumber;
        loopIterator += 1;
        String timestamp = setLoopTimestamp(s.getTimestamp());
        rememberObjectIsBusy(s, timestamp);
        String variableName = lookupVariableName(s.getObjName());
        writer.println(tabLevel0 + "let " + funcName + "_finished" + " = false;");
        writer.println(tabLevel0 + "const " + funcName + " = function () {\n" +
                tabLevel1 + "requestAnimationFrame( " + funcName + " );\n" +
                tabLevel1 + "if (" + funcName + "_finished" + ") return;\n" +
                tabLevel1 + "let timeDifference = ((new Date() - time)/1000);\n" +
                tabLevel1 + "if (timeDifference < " + timestamp + ") return;\n" +
                tabLevel1 + variableName + ".position.x = " + s.getStayPosition().getXCord() + ";\n" +
                tabLevel1 + variableName + ".position.y = " + s.getStayPosition().getYCord() + ";\n" +
                tabLevel1 + variableName + ".position.z = " + animCallNumber * 5 + ";\n" +
                tabLevel1 + "if (timeDifference > " + timestamp + " && timeDifference < " + s.getDuration() + " + " + timestamp + ") {\n" +
                tabLevel2 + "scene.add( " + variableName + " );\n" +
                tabLevel1 + "}\n" +
                tabLevel1 + "if (timeDifference > " + s.getDuration() + " + " + timestamp + " && !" + funcName +
                "_finished" + ") {\n" +
                tabLevel2 + "scene.remove(" + variableName + ");\n" +
                tabLevel2 + funcName + "_finished" + " = true;\n" +
                tabLevel1 + "}\n" +
                tabLevel1 + "renderer.render(scene, camera);\n" +
                tabLevel0 + "};\n" +
                tabLevel0  + funcName + "();");
        animCallNumber++;
        return null;
    }

    @Override
    public Void visit(Rotate r) {
        double rotatePositionX = r.getRotatePosition().getXCord();
        double rotatePositionY = r.getRotatePosition().getYCord();
        String funcName = "rotate" + loopIterator + "_" + r.getObjName();
        loopIterator += 1;
        String timestamp = setLoopTimestamp(r.getTimestamp());
        rememberObjectIsBusy(r, timestamp);
        String variableName = lookupVariableName(r.getObjName());
        writer.println(tabLevel0 + "let " + funcName + "_finished" + " = false;");
        writer.println(tabLevel0 + "const " + funcName + " = function () {\n" +
                tabLevel1 + "requestAnimationFrame( " + funcName + " );\n" +
                tabLevel2 + "if (" + funcName + "_finished" + ") return;\n" +
                tabLevel1 + "let timeDifference = ((new Date() - time)/1000);\n" +
                tabLevel1 + "if (timeDifference < " + timestamp + ") return;\n" +
                tabLevel1 + variableName + ".position.x = " + rotatePositionX + ";\n" +
                tabLevel1 + variableName + ".position.y = " + rotatePositionY + ";\n" +
                tabLevel1 + variableName + ".position.z = " + animCallNumber * 5 + ";\n" +
                tabLevel1 + "if (timeDifference > " + timestamp + " && timeDifference < " + r.getDuration() + " + " + timestamp + ") {\n" +
                tabLevel2 + "scene.add( " + variableName + " );\n" +
                tabLevel2 + variableName + ".rotation.z += Math.PI / 50;\n" +
                tabLevel1 + "}\n" +
                tabLevel1 + "if (timeDifference > " + r.getDuration() + " + " + timestamp + " && !" + funcName + "_finished" + ") {\n" +
                tabLevel2 + "scene.remove(" + variableName + ");\n" +
                tabLevel2 + variableName + ".rotation.z = 0;\n" +
                tabLevel2 + funcName + "_finished" + " = true;\n" +
                tabLevel1 + "}\n" +
                tabLevel1 + "renderer.render(scene, camera);\n" +
                tabLevel0 + "};\n" +
                tabLevel0 + funcName + "();");
        animCallNumber++;
        return null;
    }

    @Override
    public Void visit(GroupDec gd) {
        storeVariable(gd, gd.getGroupName());
        groupElements.put(gd.getGroupName(), new ArrayList<>());

        String groupName = "group" + gd.getGroupName();
        writer.println(tabLevel0 + "let " + groupName + "_finished = false;");
        writer.println(tabLevel0 + "let " + groupName + "_ready = false;");
        writer.println(tabLevel0 + "let " + groupName + ";");
        writer.println(tabLevel0 + "const " + groupName + "Func = function () {\n" +
                tabLevel1 + "requestAnimationFrame( " + groupName + "Func );\n" +
                tabLevel1 + "if (" + groupName + "_finished) return;\n" +
                tabLevel1 + groupName + " = new THREE.Group();");

        List<TerminalNode> groupedElements = gd.getGroupedElements();
        Collections.reverse(groupedElements);
        int zCoord = 0;
        for (int i = 0; i < groupedElements.size(); i++) {
            checkIfElementIsDeclared(groupedElements.get(i).getText());
            groupElements.get(gd.getGroupName()).add(groupedElements.get(i).getText());
            String elementMesh = groupedElements.get(i).getText() + "Mesh";
            writer.println(tabLevel1 + "if (" + elementMesh + "_ready) {\n" +
                    tabLevel2 + groupName + ".add( " + elementMesh + " );");
            writer.println(tabLevel2 + groupedElements.get(i).getText() + "Mesh.position.z = " + zCoord + ";");
            writer.println(tabLevel1 + "}");
            int zAdd = getZAdder(groupedElements, i);
            zCoord += zAdd;
        }
        String condition = checkIfAllShapesReady(groupedElements);
        writer.println(tabLevel1 + "if (" + condition + ") {");
        writer.println(tabLevel2 + groupName + "_ready = true;");
        writer.println(tabLevel2 + groupName + "_finished = true;");
        writer.println(tabLevel1 + "}");
        writer.println(tabLevel1 + "renderer.render(scene, camera);");
        writer.println(tabLevel0 + "}");
        writer.println(tabLevel0 + groupName + "Func();");
        return null;
    }

    @Override
    public Void visit(Loop l) {
        for (int i = l.getStart(); i <= l.getEnd(); i += l.getIncrement()) {
            loopCounter = i;
            for (Statement loopStatement : l.getLoopStatements()) {
                if (loopStatement instanceof FuncCall) {
                    FuncCall fc = (FuncCall) loopStatement;
                    try {
                        Integer.parseInt(fc.getStartTime().replaceAll("[^0-9]", ""));
                    } catch (NumberFormatException e) {
                        fc.setStartTime(l.getStart() + "s");
                    }
                }
                loopStatement.accept(this);
            }
        }
        return null;
    }

    @Override
    public Void visit(FuncCall fc) {
        int repeat = fc.getRepeatNumTimes();
        AnimationDef ad = animationDefTable.get(fc.getFuncName());
        if (ad == null) {
            throw new RuntimeException("Animation definition \'" + fc.getFuncName() + "\' has not been declared");
        }
        for (int i = 0; i < repeat; i++) {
            int addTime = Integer.parseInt(fc.getStartTime().replaceAll("[^0-9]", ""));
            for (AnimStatement as : ad.getAnimStatements()) {
                as.setObjName(fc.getInput().getVar1());
                if (as instanceof Move) {
                    Move m = (Move) as;
                    m.getStartPosition().setXAdjustment(Integer.parseInt(fc.getInput().getVar2()));
                    m.getStartPosition().setYAdjustment(Integer.parseInt(fc.getInput().getVar2()));
                    m.getEndPosition().setXAdjustment(Integer.parseInt(fc.getInput().getVar2()));
                    m.getEndPosition().setYAdjustment(Integer.parseInt(fc.getInput().getVar2()));
                } else if (as instanceof Rotate) {
                    Rotate r = (Rotate) as;
                    r.getRotatePosition().setXAdjustment(Integer.parseInt(fc.getInput().getVar2()));
                    r.getRotatePosition().setYAdjustment(Integer.parseInt(fc.getInput().getVar2()));
                } else {
                    Stay s = (Stay) as;
                    s.getStayPosition().setXAdjustment(Integer.parseInt(fc.getInput().getVar2()));
                    s.getStayPosition().setYAdjustment(Integer.parseInt(fc.getInput().getVar2()));
                }
                int oldStartTime = Integer.parseInt(as.getTimestamp());
                String newStartTime = Integer.toString(oldStartTime + addTime - animationDefTimeKeeper.get(fc.getFuncName()));
                as.setTimestamp(newStartTime);
                as.accept(this);
            }
            animationDefTimeKeeper.put(fc.getFuncName(), addTime );
            fc.setStartTime(Integer.toString(addTime+animationDefDurationTable.get(fc.getFuncName())));
        }
        return null;
    }

    @Override
    public Void visit(AnimationDef ad) {
        if (!animationDefTable.containsKey(ad.getFuncName())){
            animationDefTable.put(ad.getFuncName(), ad);
        } else {
            throw new RuntimeException("An animation definition with that name already exists!");
        }

        animationDefTimeKeeper.put(ad.getFuncName(),0);
        int totalDuration = 0;
        for (AnimStatement as : ad.getAnimStatements()) {
            totalDuration = Integer.max(totalDuration, Integer.parseInt(as.getTimestamp()) + as.getDuration());
        }
        animationDefDurationTable.put(ad.getFuncName(),totalDuration);
        return null;
    }

    @Override
    public Void visit(Input i) {
        // does nothing, but must keep in to avoid error
        return null;
    }

    private void createThreeJsShape(ShapeDec s, String geometryName) {
        String materialName = s.getShapeName() + "Material";
        String meshName = s.getShapeName() + "Mesh";
        String basicMaterial = tabLevel0 + "const " + materialName + " = new THREE.MeshBasicMaterial( { color: '" +
                ColourPicker.getColour(s.getColour()) + "' } );";
        writer.println(basicMaterial);
        writer.println(tabLevel0 + "const " + meshName + " = new THREE.Mesh( " + geometryName + ", "
                + materialName + " );\n" +
                tabLevel0 + "let " + meshName + "_ready = true;");
    }

    private void storeVariable(Statement variable, String variableName) {
        if (symbolTable.containsKey(variableName)) {
            throw new RuntimeException("You cannot create more than one element with the same name");
        }
        symbolTable.put(variableName, variable);
    }

    private String lookupVariableName(String elementName) {
        Statement statement = symbolTable.get(elementName);
        if (statement instanceof GroupDec) {
            return "group" + ((GroupDec) statement).getGroupName();
        }
        return elementName + "Mesh";
    }

    private String checkIfAllShapesReady(List<TerminalNode> elements) {
        String andClause = elements.get(0).getText() + "Mesh_ready";
        for (int i = 1; i < elements.size(); i++) {
            andClause += " && " + elements.get(i).getText() + "Mesh_ready";
        }
        return andClause;
    }

    private int getZAdder(List<TerminalNode> elements, int curr) {
        Statement currElement = symbolTable.get(elements.get(curr).getText());
        if (currElement instanceof ShapeDec) {
            String currShape = ((ShapeDec) currElement).getShape();
            if (currShape.equals("cone") || currShape.equals("sphere")) {
                return ((ShapeDec) currElement).getShapeDimensions().getRadius() * 2;
            } else if (currShape.equals("cube")) {
                return ((ShapeDec) currElement).getShapeDimensions().getDepth();
            }
        }
        return 1;
    }

    private void checkIfElementIsDeclared(String elementKey) {
        if (!symbolTable.containsKey(elementKey)) {
            throw new RuntimeException("You can't group elements that have not been created");
        }
    }

    private String setLoopTimestamp(String timestamp) {
        String actualTimestamp = "";
        actualTimestamp = timestamp != "" ? timestamp : Integer.toString(loopCounter);
        return actualTimestamp;
    }

    private void rememberObjectIsBusy(AnimStatement as, String st) {
        String name = as.getObjName();
        int startTime = Integer.parseInt(st);
        int duration = as.getDuration();

        if (groupElements.containsKey(name)) {
            for (String itemInGroup : groupElements.get(name)) {
                for (int i = 0; i < duration; i++) {
                    if (!objectBusyTimes.get(itemInGroup).contains(startTime + i)) {
                        objectBusyTimes.get(itemInGroup).add(startTime + i);
                    } else {
                        throw new RuntimeException("An element (even in a group) can only do one animation at a time!");
                    }
                }
            }
        } else {
            for (int i = 0; i < duration; i++) {
                if (objectBusyTimes.get(name) == null) {
                    throw new RuntimeException("Element \'" + name + "\' has not been declared");
                }
                if (!objectBusyTimes.get(name).contains(startTime + i)) {
                    objectBusyTimes.get(name).add(startTime + i);
                } else {
                    throw new RuntimeException("An element can only do one animation at a time!");
                }
            }
        }
    }
}
