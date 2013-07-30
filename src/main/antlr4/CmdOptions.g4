grammar CmdOptions;

//
// This grammar parsers commands of the form:
// command --opt1 <arg1> --opt2 <arg2>
//

//init : command (command)*;

command:
     name option*
    ;

option:
    WSPACE* HYPHEN HYPHEN? name (WSPACE+ value)?
    ;

name:
    NAME+
    ;

value:
    (NAME | VALUECHARS )+
    ;

NAME:
      ('a'..'z' | 'A'..'Z' | '0'..'9' | '_')+
      ;

VALUECHARS:
   ('\u0021' | '\u0023'..'\u0026' | '\u0028'..'\u002b' | '\u002e' | '\u003a'..'\u003c' | '\u003e'..'\u0040' |
   '\u005b'..'\u005e' | '\u0060' | '\u007b'..'\u007e' )+
  ;

HYPHEN:
    '-'
    ;

WSPACE:
  (' ' |'\t')
  ;
