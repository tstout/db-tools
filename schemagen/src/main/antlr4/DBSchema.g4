grammar DBSchema;

schemadef:
    modVersion* |
    tabledef*
    ;

modVersion:
    'version' '(' versionElement+ ')'
    ;

versionElement:
    'version:' versionName |
    'descr:' descrText |
    'minor:' minorVersion |
    'major:' majorVersion |
    'point:' pointVersion
    ;

minorVersion : INT
    ;

majorVersion : INT
    ;

pointVersion : INT
    ;

versionName : NAME
    ;

descrText : STRING_LITERAL
    ;

tabledef:
    'table' tablename '(' colDef? (',' colDef)* ')'
    ;

colType : 'int' | 'varchar' | 'bool'
    ;

colAttribute : 'pk' | 'autoinc' | 'nullable'
    ;

colName : NAME
        ;
          
colDef : colName colType (colAttribute)*
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

STRING_LITERAL: '\''(.)*?'\'';

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

