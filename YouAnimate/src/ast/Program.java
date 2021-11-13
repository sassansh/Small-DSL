package ast;

import java.util.List;

public class Program extends Node {
    private final CanvasColour canvasColour;
    private final List<Statement> statements;
    private final String start = """
            <!DOCTYPE html>
                <html>
                    <head>
                        <meta charset="utf-8">
                        <title>YouAnimate</title>
                        <style>
                            body { margin: 0; }
                        </style>
                    </head>
                    <body>
                        <script src="three.js"></script>
                        <script>
                            const scene = new THREE.Scene();
                            const renderer = new THREE.WebGLRenderer();
                            renderer.setSize( window.innerWidth, window.innerHeight );
                            document.body.appendChild( renderer.domElement );
                            const camera = new THREE.PerspectiveCamera( 57, 1920 / 1080, 0.1, 1000 );
                            camera.position.z = 1000;
                            camera.position.x = 960;
                            camera.position.y = 540;
                            let time = new Date();
            """;
    private final String end = """
                        const animate = function () {
                            requestAnimationFrame( animate );
                            renderer.render( scene, camera );
                        };
                        animate();
                        </script>
            	</body>
            </html>
            """;

    public Program(CanvasColour canvasColour, List<Statement> statements) {
        this.canvasColour = canvasColour;
        this.statements = statements;
    }

    public CanvasColour getCanvasColour() {
        return canvasColour;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public String getStart() { return start; }

    public String getEnd() { return end; }

    @Override
    public <T> T accept(YouAnimateVisitor<T> v) {
        return v.visit(this);
    }
}
