grammar CommonAnalytics;

tokens {LOAD,TO,SHOW,COLUMNS,SUMMARIZE,FILTER,KEYS,JOIN,MATCH,TRUE,FALSE,AND,OR,NOT,AS}


script: (sentence)*;
alias_name: (LETTER|DIGIT|valid_id_char)+;
sentence: 	load_sentence 
			| show_sentence 
			| select_sentence 
			|  join_sentence 
			| summarize_sentence ;
			
load_sentence: 'LOAD' file_name 'TO' collection_name 'STRUCTURE' '(' structure_definition_list ')'  eol;
show_sentence: 'SHOW' collection_name eol;
select_sentence: collection_name '=' 'SELECT' collection_name  '(' expression_list ')' 'FILTER' '(' (condition_list)? ')' eol;
summarize_sentence: collection_name '=' 'SUMMARIZE' collection_name  '(' (summary_expression_list)? ')' 'KEYS' '(' (groupby_list)? ')' eol; 
join_sentence: collection_name '=' 'JOIN' collection_name 'AND' collection_name 'COLUMNS' '(' expression_list ')' 'MATCH' '(' (matching_list)? ')' eol;
file_name: string_literal;
collection_name: (database_name '.' )? alias_name;
eol: ';';
expression_list: '*'|(expression|expression(',' expression_list)*);
condition_list: boolean_expression | boolean_expression (',' condition_list)*;
groupby_list: '*'| groupby_clause|groupby_clause(',' groupby_list)*;
summary_expression_list:summary_expression_and_alias|summary_expression_and_alias(',' summary_expression_list)*;
matching_list:matching_clause|matching_clause(',' matching_list)*;
database_name: alias_name;
expression:simple_expression|'(' expression ')'|'[' expression ']'|'{' expression '}';
groupby_clause: collection_name;
summary_expression_and_alias: summary_expression 'AS' alias_name;
summary_expression: ( 'SUM(' expression ')') | ('AVG(' expression ')') | ('COUNT()');
matching_clause: collection_name '=' collection_name;
valid_id_char: '-'|'_';
valid_file_path_char: valid_id_char|'/'|'.';
simple_expression: string_expression | boolean_expression | numeric_expression ;
string_expression: 
	string_literal | collection_name | string_literal string_operator string_literal ;
boolean_expression: 
	boolean_constant | 
	collection_name | 
	boolean_expression 'AND' boolean_expression | 
	boolean_expression 'OR' boolean_expression | 
	'NOT' boolean_expression | 
	'(' boolean_expression ')' |
	boolean_expression '==' boolean_expression |
	comparison ;
boolean_constant: 'TRUE' | 'FALSE';
comparison: numeric_expression comparison_operator numeric_expression | string_expression comparison_operator string_expression ;
numeric_expression: number | collection_name | numeric_expression numeric_operator numeric_expression ;
comparison_operator: '>'|'<'|'>='|'<='|'==';
numeric_operator : '+'|'-'|'*'|'/';
string_operator : '+';
string_literal: '\'' ( '$' '\''? | ~('$' | '\'') )* '\'' ;
brackets_expression: '[' expression_list ']';
braces_expression: '{' expression_list '}';
numeric_operation: number | '(' expression ')' | expression '+' expression | expression '/' expression | expression '-' expression | expression '*' expression;
string_operation: string_literal | collection_name | string_operation '+' string_operation;
number: (DIGIT)+ | (DIGIT)+ '.' (DIGIT)+;
structure_definition_list: structure_definition_field (',' structure_definition_list)*;
structure_definition_field: alias_name ':' data_type;
data_type: 'INT'|'STRING'|'BOOLEAN';
LETTER: [A-Z]|[a-z] ;
DIGIT: [0-9] ;
WS: [ \n\t\r]+ -> skip;
