grammar DBSchema;


//
// table log (
//
schemadef:
    tabledef*;   // more to follow...

tabledef:
    WSPACE* 'table' WSPACE* tablename WSPACE* '(' WSPACE* coldef* WSPACE* ')'
    ;

tablename:
    NAME+
    ;

coldef:
    NAME (intcol | charcol) WSPACE*
    |
    NAME (intcol | charcol) WSPACE* ','
    ;

intcol:
    INT_TYPE
    ;

charcol:
    CHAR_TYPE WSPACE* '(' INT ')'
    ;

coltype:
    INT
    ;

COMMENT : '/*' .*? '*/' -> skip
    ;

LINE_COMMENT:
    '//' .*? ('\n'|'\r') -> skip
    ;

NAME:
    ('a'..'z' | 'A'..'Z' | '0'..'9' | '_')+
    ;

WSPACE:
  [ \t\n\r] -> skip
  ;

INT:
    [0-9]+
    ;

//
// Column Types
//
INT_TYPE:
    'int'
   ;

CHAR_TYPE:
    'char'
    ;

BIT_TYPE:
    'bit'
    ;

DECIMAL_TYPE:
    'decimal'
    ;

//
// Misc. keywords
//
FK:
    'fk'
    ;

PK:
    'pk'
    ;