lexer grammar YouAnimateLexer ;

CANVAS_COLOUR: 'canvas colour:' ;
HEIGHT: 'height=' -> mode(NUM_MODE) ;
WIDTH: 'width=' -> mode(NUM_MODE) ;
BASE: 'base=' -> mode(NUM_MODE) ;
DEPTH: 'depth=' -> mode(NUM_MODE) ;
RADIUS: 'radius=' -> mode(NUM_MODE) ;
FONT_SIZE: 'font-size' -> mode(NUM_MODE) ;
GROUP_ON: 'on' -> mode(TEXT_MODE) ;

FREE_TEXT: '"' [a-zA-Z0-9_!@#$%^&'? ]+ '"' ;

SHAPE_START: 'shape' -> mode(TEXT_MODE) ;
TEXT_START: 'text' -> mode(TEXT_MODE) ;
GROUP_START: 'group' -> mode(TEXT_MODE) ;

FUNC_START: 'play' -> mode(TEXT_MODE) ;
FUNC_REPEAT: 'repeat' -> mode(NUM_MODE) ;
FUNC_TIMES: 'times' -> mode(TEXT_MODE) ;

IS: 'is' -> mode(TEXT_MODE) ;

MOVE_START: 'move' -> mode(TEXT_MODE) ;
STAY_START: 'stay' -> mode(TEXT_MODE) ;
ROTATE_START: 'rotate' -> mode(TEXT_MODE) ;

FROM: 'from' ;
STARTING_AT: 'starting at' -> mode(TEXT_MODE) ;
FOR: 'for' -> mode(TEXT_MODE) ;
TO: 'to' ;
AT: 'at' ;

LOOP_START: 'loop' -> mode(TEXT_MODE) ;
LOOP_INCREMENT: 'increment' -> mode(NUM_MODE) ;
COLON: ':' ;

SHAPE: 'circle' | 'triangle' | 'square' | 'rectangle' | 'sphere' | 'cube' | 'cone' ;
COLOUR: 'red' | 'green' | 'yellow' | 'blue' | 'orange' | 'purple' | 'white' | 'black' ;
WS : [\r\n\t ]+ -> channel(HIDDEN) ;

PLUS: '+' -> mode(TEXT_MODE) ;
MULTIPLY: '*' -> mode(TEXT_MODE) ;
TEXT_PARAM_START: '(' -> mode(TEXT_MODE) ;
TEXT_PARAM_SEP: ',' -> mode(TEXT_MODE) ;
TEXT_PARAM_END: ')' ;

NUM_PARAM_START: '[' -> mode(NUM_MODE) ;
NUM_PARAM_SEP: '|' -> mode(NUM_MODE) ;
NUM_PARAM_END: ']' ;

ANIMATION_DEF_START: 'animation-def:' -> mode(TEXT_MODE) ;
WITH_INPUT: 'with input' ;

mode TEXT_MODE ;
TEXT: [a-zA-Z0-9_]+ -> mode(DEFAULT_MODE) ;
WS_TEXT : [\r\n\t ]+ -> channel(HIDDEN) ;

mode NUM_MODE ;
NUM: [0-9]+ -> mode(DEFAULT_MODE) ;
WS_NUM : [\r\n\t ]+ -> channel(HIDDEN) ;