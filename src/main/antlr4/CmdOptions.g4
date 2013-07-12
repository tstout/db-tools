grammar CmdOptions;

// parser rules start with lowercase letters, lexer rules with uppercase

option : '-' command | '--' command;

command : NUMBER | ALPHA;

fragment DIGIT  : '0'..'9' ;
fragment LETTER : 'a'..'z' | 'A'..'Z' ;

NUMBER : DIGIT+ ;
ALPHA : LETTER+ ;

