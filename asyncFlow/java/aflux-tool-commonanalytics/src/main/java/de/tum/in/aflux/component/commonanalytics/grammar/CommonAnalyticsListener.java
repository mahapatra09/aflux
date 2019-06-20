/*
 *
 *  *
 *  * aFlux: JVM based IoT Mashup Tool
 *  * Copyright (C) 2018  Tanmaya Mahapatra
 *  *
 *  * This file is part of aFlux.
 *  *
 *  * aFlux is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, version 3 of the License.
 *  *
 *  * aFlux is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with aFlux.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 */

package de.tum.in.aflux.component.commonanalytics.grammar;

// Generated from CommonAnalytics.g4 by ANTLR 4.5
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CommonAnalyticsParser}.
 */
public interface CommonAnalyticsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#script}.
	 * @param ctx the parse tree
	 */
	void enterScript(CommonAnalyticsParser.ScriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#script}.
	 * @param ctx the parse tree
	 */
	void exitScript(CommonAnalyticsParser.ScriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#alias_name}.
	 * @param ctx the parse tree
	 */
	void enterAlias_name(CommonAnalyticsParser.Alias_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#alias_name}.
	 * @param ctx the parse tree
	 */
	void exitAlias_name(CommonAnalyticsParser.Alias_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterSentence(CommonAnalyticsParser.SentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitSentence(CommonAnalyticsParser.SentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#load_sentence}.
	 * @param ctx the parse tree
	 */
	void enterLoad_sentence(CommonAnalyticsParser.Load_sentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#load_sentence}.
	 * @param ctx the parse tree
	 */
	void exitLoad_sentence(CommonAnalyticsParser.Load_sentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#show_sentence}.
	 * @param ctx the parse tree
	 */
	void enterShow_sentence(CommonAnalyticsParser.Show_sentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#show_sentence}.
	 * @param ctx the parse tree
	 */
	void exitShow_sentence(CommonAnalyticsParser.Show_sentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#select_sentence}.
	 * @param ctx the parse tree
	 */
	void enterSelect_sentence(CommonAnalyticsParser.Select_sentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#select_sentence}.
	 * @param ctx the parse tree
	 */
	void exitSelect_sentence(CommonAnalyticsParser.Select_sentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#summarize_sentence}.
	 * @param ctx the parse tree
	 */
	void enterSummarize_sentence(CommonAnalyticsParser.Summarize_sentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#summarize_sentence}.
	 * @param ctx the parse tree
	 */
	void exitSummarize_sentence(CommonAnalyticsParser.Summarize_sentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#join_sentence}.
	 * @param ctx the parse tree
	 */
	void enterJoin_sentence(CommonAnalyticsParser.Join_sentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#join_sentence}.
	 * @param ctx the parse tree
	 */
	void exitJoin_sentence(CommonAnalyticsParser.Join_sentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#file_name}.
	 * @param ctx the parse tree
	 */
	void enterFile_name(CommonAnalyticsParser.File_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#file_name}.
	 * @param ctx the parse tree
	 */
	void exitFile_name(CommonAnalyticsParser.File_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#collection_name}.
	 * @param ctx the parse tree
	 */
	void enterCollection_name(CommonAnalyticsParser.Collection_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#collection_name}.
	 * @param ctx the parse tree
	 */
	void exitCollection_name(CommonAnalyticsParser.Collection_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#eol}.
	 * @param ctx the parse tree
	 */
	void enterEol(CommonAnalyticsParser.EolContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#eol}.
	 * @param ctx the parse tree
	 */
	void exitEol(CommonAnalyticsParser.EolContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#expression_list}.
	 * @param ctx the parse tree
	 */
	void enterExpression_list(CommonAnalyticsParser.Expression_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#expression_list}.
	 * @param ctx the parse tree
	 */
	void exitExpression_list(CommonAnalyticsParser.Expression_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#condition_list}.
	 * @param ctx the parse tree
	 */
	void enterCondition_list(CommonAnalyticsParser.Condition_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#condition_list}.
	 * @param ctx the parse tree
	 */
	void exitCondition_list(CommonAnalyticsParser.Condition_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#groupby_list}.
	 * @param ctx the parse tree
	 */
	void enterGroupby_list(CommonAnalyticsParser.Groupby_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#groupby_list}.
	 * @param ctx the parse tree
	 */
	void exitGroupby_list(CommonAnalyticsParser.Groupby_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#summary_expression_list}.
	 * @param ctx the parse tree
	 */
	void enterSummary_expression_list(CommonAnalyticsParser.Summary_expression_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#summary_expression_list}.
	 * @param ctx the parse tree
	 */
	void exitSummary_expression_list(CommonAnalyticsParser.Summary_expression_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#matching_list}.
	 * @param ctx the parse tree
	 */
	void enterMatching_list(CommonAnalyticsParser.Matching_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#matching_list}.
	 * @param ctx the parse tree
	 */
	void exitMatching_list(CommonAnalyticsParser.Matching_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#database_name}.
	 * @param ctx the parse tree
	 */
	void enterDatabase_name(CommonAnalyticsParser.Database_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#database_name}.
	 * @param ctx the parse tree
	 */
	void exitDatabase_name(CommonAnalyticsParser.Database_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(CommonAnalyticsParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(CommonAnalyticsParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#groupby_clause}.
	 * @param ctx the parse tree
	 */
	void enterGroupby_clause(CommonAnalyticsParser.Groupby_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#groupby_clause}.
	 * @param ctx the parse tree
	 */
	void exitGroupby_clause(CommonAnalyticsParser.Groupby_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#summary_expression_and_alias}.
	 * @param ctx the parse tree
	 */
	void enterSummary_expression_and_alias(CommonAnalyticsParser.Summary_expression_and_aliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#summary_expression_and_alias}.
	 * @param ctx the parse tree
	 */
	void exitSummary_expression_and_alias(CommonAnalyticsParser.Summary_expression_and_aliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#summary_expression}.
	 * @param ctx the parse tree
	 */
	void enterSummary_expression(CommonAnalyticsParser.Summary_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#summary_expression}.
	 * @param ctx the parse tree
	 */
	void exitSummary_expression(CommonAnalyticsParser.Summary_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#matching_clause}.
	 * @param ctx the parse tree
	 */
	void enterMatching_clause(CommonAnalyticsParser.Matching_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#matching_clause}.
	 * @param ctx the parse tree
	 */
	void exitMatching_clause(CommonAnalyticsParser.Matching_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#valid_id_char}.
	 * @param ctx the parse tree
	 */
	void enterValid_id_char(CommonAnalyticsParser.Valid_id_charContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#valid_id_char}.
	 * @param ctx the parse tree
	 */
	void exitValid_id_char(CommonAnalyticsParser.Valid_id_charContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#valid_file_path_char}.
	 * @param ctx the parse tree
	 */
	void enterValid_file_path_char(CommonAnalyticsParser.Valid_file_path_charContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#valid_file_path_char}.
	 * @param ctx the parse tree
	 */
	void exitValid_file_path_char(CommonAnalyticsParser.Valid_file_path_charContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#simple_expression}.
	 * @param ctx the parse tree
	 */
	void enterSimple_expression(CommonAnalyticsParser.Simple_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#simple_expression}.
	 * @param ctx the parse tree
	 */
	void exitSimple_expression(CommonAnalyticsParser.Simple_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#string_expression}.
	 * @param ctx the parse tree
	 */
	void enterString_expression(CommonAnalyticsParser.String_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#string_expression}.
	 * @param ctx the parse tree
	 */
	void exitString_expression(CommonAnalyticsParser.String_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#boolean_expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_expression(CommonAnalyticsParser.Boolean_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#boolean_expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_expression(CommonAnalyticsParser.Boolean_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#boolean_constant}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_constant(CommonAnalyticsParser.Boolean_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#boolean_constant}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_constant(CommonAnalyticsParser.Boolean_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(CommonAnalyticsParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(CommonAnalyticsParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#numeric_expression}.
	 * @param ctx the parse tree
	 */
	void enterNumeric_expression(CommonAnalyticsParser.Numeric_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#numeric_expression}.
	 * @param ctx the parse tree
	 */
	void exitNumeric_expression(CommonAnalyticsParser.Numeric_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#comparison_operator}.
	 * @param ctx the parse tree
	 */
	void enterComparison_operator(CommonAnalyticsParser.Comparison_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#comparison_operator}.
	 * @param ctx the parse tree
	 */
	void exitComparison_operator(CommonAnalyticsParser.Comparison_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#numeric_operator}.
	 * @param ctx the parse tree
	 */
	void enterNumeric_operator(CommonAnalyticsParser.Numeric_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#numeric_operator}.
	 * @param ctx the parse tree
	 */
	void exitNumeric_operator(CommonAnalyticsParser.Numeric_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#string_operator}.
	 * @param ctx the parse tree
	 */
	void enterString_operator(CommonAnalyticsParser.String_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#string_operator}.
	 * @param ctx the parse tree
	 */
	void exitString_operator(CommonAnalyticsParser.String_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#string_literal}.
	 * @param ctx the parse tree
	 */
	void enterString_literal(CommonAnalyticsParser.String_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#string_literal}.
	 * @param ctx the parse tree
	 */
	void exitString_literal(CommonAnalyticsParser.String_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#brackets_expression}.
	 * @param ctx the parse tree
	 */
	void enterBrackets_expression(CommonAnalyticsParser.Brackets_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#brackets_expression}.
	 * @param ctx the parse tree
	 */
	void exitBrackets_expression(CommonAnalyticsParser.Brackets_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#braces_expression}.
	 * @param ctx the parse tree
	 */
	void enterBraces_expression(CommonAnalyticsParser.Braces_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#braces_expression}.
	 * @param ctx the parse tree
	 */
	void exitBraces_expression(CommonAnalyticsParser.Braces_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#numeric_operation}.
	 * @param ctx the parse tree
	 */
	void enterNumeric_operation(CommonAnalyticsParser.Numeric_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#numeric_operation}.
	 * @param ctx the parse tree
	 */
	void exitNumeric_operation(CommonAnalyticsParser.Numeric_operationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#string_operation}.
	 * @param ctx the parse tree
	 */
	void enterString_operation(CommonAnalyticsParser.String_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#string_operation}.
	 * @param ctx the parse tree
	 */
	void exitString_operation(CommonAnalyticsParser.String_operationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(CommonAnalyticsParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(CommonAnalyticsParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#structure_definition_list}.
	 * @param ctx the parse tree
	 */
	void enterStructure_definition_list(CommonAnalyticsParser.Structure_definition_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#structure_definition_list}.
	 * @param ctx the parse tree
	 */
	void exitStructure_definition_list(CommonAnalyticsParser.Structure_definition_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#structure_definition_field}.
	 * @param ctx the parse tree
	 */
	void enterStructure_definition_field(CommonAnalyticsParser.Structure_definition_fieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#structure_definition_field}.
	 * @param ctx the parse tree
	 */
	void exitStructure_definition_field(CommonAnalyticsParser.Structure_definition_fieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommonAnalyticsParser#data_type}.
	 * @param ctx the parse tree
	 */
	void enterData_type(CommonAnalyticsParser.Data_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommonAnalyticsParser#data_type}.
	 * @param ctx the parse tree
	 */
	void exitData_type(CommonAnalyticsParser.Data_typeContext ctx);
}