parser grammar YouAnimateParser;
options { tokenVocab=YouAnimateLexer; }

program : canvasColour statement+ ;
canvasColour: CANVAS_COLOUR COLOUR ;
statement: shapeDec | textDec | groupDec | animationDef | loop | funcCall | animStatement;
shapeDec: SHAPE_START TEXT COLON COLOUR SHAPE (RADIUS NUM | HEIGHT NUM | WIDTH NUM | BASE NUM | DEPTH NUM)+;
textDec: TEXT_START TEXT COLON COLOUR FREE_TEXT FONT_SIZE NUM ;
groupDec: GROUP_START TEXT IS TEXT (GROUP_ON TEXT)+ ;

xyCord: NUM_PARAM_START NUM (PLUS TEXT)? NUM_PARAM_SEP NUM (PLUS TEXT)? NUM_PARAM_END ;

move: MOVE_START TEXT FROM xyCord TO xyCord STARTING_AT TEXT FOR TEXT ;
stay: STAY_START TEXT AT xyCord STARTING_AT TEXT FOR TEXT ;
rotate: ROTATE_START TEXT AT xyCord STARTING_AT TEXT FOR TEXT ;
animStatement: move | stay | rotate ;

loop: LOOP_START TEXT FROM NUM_PARAM_START NUM NUM_PARAM_END TO NUM_PARAM_START NUM NUM_PARAM_END LOOP_INCREMENT NUM COLON loopStatement+ ;
loopStatement: funcCall | animStatement ;

input: TEXT_PARAM_START TEXT TEXT_PARAM_SEP TEXT (MULTIPLY TEXT|PLUS TEXT)? TEXT_PARAM_END ;
funcCall: (FUNC_START | FUNC_REPEAT NUM FUNC_TIMES) TEXT input STARTING_AT TEXT ;

animationDef: ANIMATION_DEF_START TEXT WITH_INPUT input COLON (animStatement)+ ;