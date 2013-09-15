grammar DBSchema;

schemadef:
    tabledef*;   // more to follow...

tabledef:
    'table' tablename '(' colDef? (',' colDef)* ')'
    ;

colType : 'int' | 'char' | 'bit'
        ;

colName : NAME
        ;
          
colDef : colName colType
       ;          

tablename:
    NAME
    ;

COMMENT : '/*' .*? '*/' -> skip
    ;

LINE_COMMENT:
    '//' .*? ('\n'|'\r') -> skip
    ;

fragment
COLNAME : NAME;

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