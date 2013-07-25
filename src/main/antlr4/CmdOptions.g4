grammar CmdOptions;

// parser rules start with lowercase letters, lexer rules with uppercase

init : command (command)*;

command : DASH ALPHA (ALPHA)?;

DASH : '--' | '-';

ALPHA : ('a'..'z'|'A'..'Z'|'0'..'9'|'.')+;

WS
    : [ \t\r\n]+ -> skip
    ;
