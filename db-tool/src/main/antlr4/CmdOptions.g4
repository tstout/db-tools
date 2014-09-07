grammar CmdOptions;

//
// This grammar defines commands of the form:
// command --opt1 <arg1> --opt2 <arg2>
//

command:
     name option*
    ;

//
// Is explicit WSPACE necessary here?
//
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
   ('!' | '#'..'&' | '('..'+' | '.' | ':'..'<' | '>'..'@' |
   '['..'^' | '`' | '{'..'~' )+
  ;

HYPHEN:
    '-'
    ;

WSPACE:
  (' ' | '\t')
  ;
