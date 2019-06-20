

/*
 * aFlux: JVM based IoT Mashup Tool
 * Copyright [2019] [Tanmaya Mahapatra]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.tum.in.aflux.component.commonanalytics.grammar;

// Generated from CommonAnalytics.g4 by ANTLR 4.5
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CommonAnalyticsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, LETTER=47, DIGIT=48, WS=49, LOAD=50, TO=51, SHOW=52, COLUMNS=53, 
		SUMMARIZE=54, FILTER=55, KEYS=56, JOIN=57, MATCH=58, TRUE=59, FALSE=60, 
		AND=61, OR=62, NOT=63, AS=64;
	public static final int
		RULE_script = 0, RULE_alias_name = 1, RULE_sentence = 2, RULE_load_sentence = 3, 
		RULE_show_sentence = 4, RULE_select_sentence = 5, RULE_summarize_sentence = 6, 
		RULE_join_sentence = 7, RULE_file_name = 8, RULE_collection_name = 9, 
		RULE_eol = 10, RULE_expression_list = 11, RULE_condition_list = 12, RULE_groupby_list = 13, 
		RULE_summary_expression_list = 14, RULE_matching_list = 15, RULE_database_name = 16, 
		RULE_expression = 17, RULE_groupby_clause = 18, RULE_summary_expression_and_alias = 19, 
		RULE_summary_expression = 20, RULE_matching_clause = 21, RULE_valid_id_char = 22, 
		RULE_valid_file_path_char = 23, RULE_simple_expression = 24, RULE_string_expression = 25, 
		RULE_boolean_expression = 26, RULE_boolean_constant = 27, RULE_comparison = 28, 
		RULE_numeric_expression = 29, RULE_comparison_operator = 30, RULE_numeric_operator = 31, 
		RULE_string_operator = 32, RULE_string_literal = 33, RULE_brackets_expression = 34, 
		RULE_braces_expression = 35, RULE_numeric_operation = 36, RULE_string_operation = 37, 
		RULE_number = 38, RULE_structure_definition_list = 39, RULE_structure_definition_field = 40, 
		RULE_data_type = 41;
	public static final String[] ruleNames = {
		"script", "alias_name", "sentence", "load_sentence", "show_sentence", 
		"select_sentence", "summarize_sentence", "join_sentence", "file_name", 
		"collection_name", "eol", "expression_list", "condition_list", "groupby_list", 
		"summary_expression_list", "matching_list", "database_name", "expression", 
		"groupby_clause", "summary_expression_and_alias", "summary_expression", 
		"matching_clause", "valid_id_char", "valid_file_path_char", "simple_expression", 
		"string_expression", "boolean_expression", "boolean_constant", "comparison", 
		"numeric_expression", "comparison_operator", "numeric_operator", "string_operator", 
		"string_literal", "brackets_expression", "braces_expression", "numeric_operation", 
		"string_operation", "number", "structure_definition_list", "structure_definition_field", 
		"data_type"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'LOAD'", "'TO'", "'STRUCTURE'", "'('", "')'", "'SHOW'", "'='", 
		"'SELECT'", "'FILTER'", "'SUMMARIZE'", "'KEYS'", "'JOIN'", "'AND'", "'COLUMNS'", 
		"'MATCH'", "'.'", "';'", "'*'", "','", "'['", "']'", "'{'", "'}'", "'AS'", 
		"'SUM('", "'AVG('", "'COUNT()'", "'-'", "'_'", "'/'", "'OR'", "'NOT'", 
		"'=='", "'TRUE'", "'FALSE'", "'>'", "'<'", "'>='", "'<='", "'+'", "'''", 
		"'$'", "':'", "'INT'", "'STRING'", "'BOOLEAN'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "LETTER", 
		"DIGIT", "WS", "LOAD", "TO", "SHOW", "COLUMNS", "SUMMARIZE", "FILTER", 
		"KEYS", "JOIN", "MATCH", "TRUE", "FALSE", "AND", "OR", "NOT", "AS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CommonAnalytics.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CommonAnalyticsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ScriptContext extends ParserRuleContext {
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public ScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_script; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterScript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitScript(this);
		}
	}

	public final ScriptContext script() throws RecognitionException {
		ScriptContext _localctx = new ScriptContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_script);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__5) | (1L << T__27) | (1L << T__28) | (1L << LETTER) | (1L << DIGIT))) != 0)) {
				{
				{
				setState(84);
				sentence();
				}
				}
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Alias_nameContext extends ParserRuleContext {
		public List<TerminalNode> LETTER() { return getTokens(CommonAnalyticsParser.LETTER); }
		public TerminalNode LETTER(int i) {
			return getToken(CommonAnalyticsParser.LETTER, i);
		}
		public List<TerminalNode> DIGIT() { return getTokens(CommonAnalyticsParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(CommonAnalyticsParser.DIGIT, i);
		}
		public List<Valid_id_charContext> valid_id_char() {
			return getRuleContexts(Valid_id_charContext.class);
		}
		public Valid_id_charContext valid_id_char(int i) {
			return getRuleContext(Valid_id_charContext.class,i);
		}
		public Alias_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alias_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterAlias_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitAlias_name(this);
		}
	}

	public final Alias_nameContext alias_name() throws RecognitionException {
		Alias_nameContext _localctx = new Alias_nameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_alias_name);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(93); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(93);
					switch (_input.LA(1)) {
					case LETTER:
						{
						setState(90);
						match(LETTER);
						}
						break;
					case DIGIT:
						{
						setState(91);
						match(DIGIT);
						}
						break;
					case T__27:
					case T__28:
						{
						setState(92);
						valid_id_char();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(95); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SentenceContext extends ParserRuleContext {
		public Load_sentenceContext load_sentence() {
			return getRuleContext(Load_sentenceContext.class,0);
		}
		public Show_sentenceContext show_sentence() {
			return getRuleContext(Show_sentenceContext.class,0);
		}
		public Select_sentenceContext select_sentence() {
			return getRuleContext(Select_sentenceContext.class,0);
		}
		public Join_sentenceContext join_sentence() {
			return getRuleContext(Join_sentenceContext.class,0);
		}
		public Summarize_sentenceContext summarize_sentence() {
			return getRuleContext(Summarize_sentenceContext.class,0);
		}
		public SentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterSentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitSentence(this);
		}
	}

	public final SentenceContext sentence() throws RecognitionException {
		SentenceContext _localctx = new SentenceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sentence);
		try {
			setState(102);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				load_sentence();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
				show_sentence();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(99);
				select_sentence();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(100);
				join_sentence();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(101);
				summarize_sentence();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Load_sentenceContext extends ParserRuleContext {
		public File_nameContext file_name() {
			return getRuleContext(File_nameContext.class,0);
		}
		public Collection_nameContext collection_name() {
			return getRuleContext(Collection_nameContext.class,0);
		}
		public Structure_definition_listContext structure_definition_list() {
			return getRuleContext(Structure_definition_listContext.class,0);
		}
		public EolContext eol() {
			return getRuleContext(EolContext.class,0);
		}
		public Load_sentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_load_sentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterLoad_sentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitLoad_sentence(this);
		}
	}

	public final Load_sentenceContext load_sentence() throws RecognitionException {
		Load_sentenceContext _localctx = new Load_sentenceContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_load_sentence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(T__0);
			setState(105);
			file_name();
			setState(106);
			match(T__1);
			setState(107);
			collection_name();
			setState(108);
			match(T__2);
			setState(109);
			match(T__3);
			setState(110);
			structure_definition_list();
			setState(111);
			match(T__4);
			setState(112);
			eol();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Show_sentenceContext extends ParserRuleContext {
		public Collection_nameContext collection_name() {
			return getRuleContext(Collection_nameContext.class,0);
		}
		public EolContext eol() {
			return getRuleContext(EolContext.class,0);
		}
		public Show_sentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_show_sentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterShow_sentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitShow_sentence(this);
		}
	}

	public final Show_sentenceContext show_sentence() throws RecognitionException {
		Show_sentenceContext _localctx = new Show_sentenceContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_show_sentence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(T__5);
			setState(115);
			collection_name();
			setState(116);
			eol();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_sentenceContext extends ParserRuleContext {
		public List<Collection_nameContext> collection_name() {
			return getRuleContexts(Collection_nameContext.class);
		}
		public Collection_nameContext collection_name(int i) {
			return getRuleContext(Collection_nameContext.class,i);
		}
		public Expression_listContext expression_list() {
			return getRuleContext(Expression_listContext.class,0);
		}
		public EolContext eol() {
			return getRuleContext(EolContext.class,0);
		}
		public Condition_listContext condition_list() {
			return getRuleContext(Condition_listContext.class,0);
		}
		public Select_sentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_sentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterSelect_sentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitSelect_sentence(this);
		}
	}

	public final Select_sentenceContext select_sentence() throws RecognitionException {
		Select_sentenceContext _localctx = new Select_sentenceContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_select_sentence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			collection_name();
			setState(119);
			match(T__6);
			setState(120);
			match(T__7);
			setState(121);
			collection_name();
			setState(122);
			match(T__3);
			setState(123);
			expression_list();
			setState(124);
			match(T__4);
			setState(125);
			match(T__8);
			setState(126);
			match(T__3);
			setState(128);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__27) | (1L << T__28) | (1L << T__31) | (1L << T__33) | (1L << T__34) | (1L << T__40) | (1L << LETTER) | (1L << DIGIT))) != 0)) {
				{
				setState(127);
				condition_list();
				}
			}

			setState(130);
			match(T__4);
			setState(131);
			eol();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Summarize_sentenceContext extends ParserRuleContext {
		public List<Collection_nameContext> collection_name() {
			return getRuleContexts(Collection_nameContext.class);
		}
		public Collection_nameContext collection_name(int i) {
			return getRuleContext(Collection_nameContext.class,i);
		}
		public EolContext eol() {
			return getRuleContext(EolContext.class,0);
		}
		public Summary_expression_listContext summary_expression_list() {
			return getRuleContext(Summary_expression_listContext.class,0);
		}
		public Groupby_listContext groupby_list() {
			return getRuleContext(Groupby_listContext.class,0);
		}
		public Summarize_sentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_summarize_sentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterSummarize_sentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitSummarize_sentence(this);
		}
	}

	public final Summarize_sentenceContext summarize_sentence() throws RecognitionException {
		Summarize_sentenceContext _localctx = new Summarize_sentenceContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_summarize_sentence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			collection_name();
			setState(134);
			match(T__6);
			setState(135);
			match(T__9);
			setState(136);
			collection_name();
			setState(137);
			match(T__3);
			setState(139);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__26))) != 0)) {
				{
				setState(138);
				summary_expression_list();
				}
			}

			setState(141);
			match(T__4);
			setState(142);
			match(T__10);
			setState(143);
			match(T__3);
			setState(145);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__27) | (1L << T__28) | (1L << LETTER) | (1L << DIGIT))) != 0)) {
				{
				setState(144);
				groupby_list();
				}
			}

			setState(147);
			match(T__4);
			setState(148);
			eol();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Join_sentenceContext extends ParserRuleContext {
		public List<Collection_nameContext> collection_name() {
			return getRuleContexts(Collection_nameContext.class);
		}
		public Collection_nameContext collection_name(int i) {
			return getRuleContext(Collection_nameContext.class,i);
		}
		public Expression_listContext expression_list() {
			return getRuleContext(Expression_listContext.class,0);
		}
		public EolContext eol() {
			return getRuleContext(EolContext.class,0);
		}
		public Matching_listContext matching_list() {
			return getRuleContext(Matching_listContext.class,0);
		}
		public Join_sentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_sentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterJoin_sentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitJoin_sentence(this);
		}
	}

	public final Join_sentenceContext join_sentence() throws RecognitionException {
		Join_sentenceContext _localctx = new Join_sentenceContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_join_sentence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			collection_name();
			setState(151);
			match(T__6);
			setState(152);
			match(T__11);
			setState(153);
			collection_name();
			setState(154);
			match(T__12);
			setState(155);
			collection_name();
			setState(156);
			match(T__13);
			setState(157);
			match(T__3);
			setState(158);
			expression_list();
			setState(159);
			match(T__4);
			setState(160);
			match(T__14);
			setState(161);
			match(T__3);
			setState(163);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__27) | (1L << T__28) | (1L << LETTER) | (1L << DIGIT))) != 0)) {
				{
				setState(162);
				matching_list();
				}
			}

			setState(165);
			match(T__4);
			setState(166);
			eol();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class File_nameContext extends ParserRuleContext {
		public String_literalContext string_literal() {
			return getRuleContext(String_literalContext.class,0);
		}
		public File_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterFile_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitFile_name(this);
		}
	}

	public final File_nameContext file_name() throws RecognitionException {
		File_nameContext _localctx = new File_nameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_file_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			string_literal();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Collection_nameContext extends ParserRuleContext {
		public Alias_nameContext alias_name() {
			return getRuleContext(Alias_nameContext.class,0);
		}
		public Database_nameContext database_name() {
			return getRuleContext(Database_nameContext.class,0);
		}
		public Collection_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collection_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterCollection_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitCollection_name(this);
		}
	}

	public final Collection_nameContext collection_name() throws RecognitionException {
		Collection_nameContext _localctx = new Collection_nameContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_collection_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(170);
				database_name();
				setState(171);
				match(T__15);
				}
				break;
			}
			setState(175);
			alias_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EolContext extends ParserRuleContext {
		public EolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterEol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitEol(this);
		}
	}

	public final EolContext eol() throws RecognitionException {
		EolContext _localctx = new EolContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_eol);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(T__16);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression_listContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<Expression_listContext> expression_list() {
			return getRuleContexts(Expression_listContext.class);
		}
		public Expression_listContext expression_list(int i) {
			return getRuleContext(Expression_listContext.class,i);
		}
		public Expression_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterExpression_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitExpression_list(this);
		}
	}

	public final Expression_listContext expression_list() throws RecognitionException {
		Expression_listContext _localctx = new Expression_listContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expression_list);
		try {
			int _alt;
			setState(191);
			switch (_input.LA(1)) {
			case T__17:
				enterOuterAlt(_localctx, 1);
				{
				setState(179);
				match(T__17);
				}
				break;
			case T__3:
			case T__19:
			case T__21:
			case T__27:
			case T__28:
			case T__31:
			case T__33:
			case T__34:
			case T__40:
			case LETTER:
			case DIGIT:
				enterOuterAlt(_localctx, 2);
				{
				setState(189);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(180);
					expression();
					}
					break;
				case 2:
					{
					setState(181);
					expression();
					setState(186);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(182);
							match(T__18);
							setState(183);
							expression_list();
							}
							} 
						}
						setState(188);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
					}
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Condition_listContext extends ParserRuleContext {
		public Boolean_expressionContext boolean_expression() {
			return getRuleContext(Boolean_expressionContext.class,0);
		}
		public List<Condition_listContext> condition_list() {
			return getRuleContexts(Condition_listContext.class);
		}
		public Condition_listContext condition_list(int i) {
			return getRuleContext(Condition_listContext.class,i);
		}
		public Condition_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterCondition_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitCondition_list(this);
		}
	}

	public final Condition_listContext condition_list() throws RecognitionException {
		Condition_listContext _localctx = new Condition_listContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_condition_list);
		try {
			int _alt;
			setState(202);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(193);
				boolean_expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				boolean_expression(0);
				setState(199);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(195);
						match(T__18);
						setState(196);
						condition_list();
						}
						} 
					}
					setState(201);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Groupby_listContext extends ParserRuleContext {
		public Groupby_clauseContext groupby_clause() {
			return getRuleContext(Groupby_clauseContext.class,0);
		}
		public List<Groupby_listContext> groupby_list() {
			return getRuleContexts(Groupby_listContext.class);
		}
		public Groupby_listContext groupby_list(int i) {
			return getRuleContext(Groupby_listContext.class,i);
		}
		public Groupby_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupby_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterGroupby_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitGroupby_list(this);
		}
	}

	public final Groupby_listContext groupby_list() throws RecognitionException {
		Groupby_listContext _localctx = new Groupby_listContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_groupby_list);
		try {
			int _alt;
			setState(214);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(204);
				match(T__17);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(205);
				groupby_clause();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(206);
				groupby_clause();
				setState(211);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(207);
						match(T__18);
						setState(208);
						groupby_list();
						}
						} 
					}
					setState(213);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Summary_expression_listContext extends ParserRuleContext {
		public Summary_expression_and_aliasContext summary_expression_and_alias() {
			return getRuleContext(Summary_expression_and_aliasContext.class,0);
		}
		public List<Summary_expression_listContext> summary_expression_list() {
			return getRuleContexts(Summary_expression_listContext.class);
		}
		public Summary_expression_listContext summary_expression_list(int i) {
			return getRuleContext(Summary_expression_listContext.class,i);
		}
		public Summary_expression_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_summary_expression_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterSummary_expression_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitSummary_expression_list(this);
		}
	}

	public final Summary_expression_listContext summary_expression_list() throws RecognitionException {
		Summary_expression_listContext _localctx = new Summary_expression_listContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_summary_expression_list);
		try {
			int _alt;
			setState(225);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(216);
				summary_expression_and_alias();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(217);
				summary_expression_and_alias();
				setState(222);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(218);
						match(T__18);
						setState(219);
						summary_expression_list();
						}
						} 
					}
					setState(224);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Matching_listContext extends ParserRuleContext {
		public Matching_clauseContext matching_clause() {
			return getRuleContext(Matching_clauseContext.class,0);
		}
		public List<Matching_listContext> matching_list() {
			return getRuleContexts(Matching_listContext.class);
		}
		public Matching_listContext matching_list(int i) {
			return getRuleContext(Matching_listContext.class,i);
		}
		public Matching_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matching_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterMatching_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitMatching_list(this);
		}
	}

	public final Matching_listContext matching_list() throws RecognitionException {
		Matching_listContext _localctx = new Matching_listContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_matching_list);
		try {
			int _alt;
			setState(236);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(227);
				matching_clause();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(228);
				matching_clause();
				setState(233);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(229);
						match(T__18);
						setState(230);
						matching_list();
						}
						} 
					}
					setState(235);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Database_nameContext extends ParserRuleContext {
		public Alias_nameContext alias_name() {
			return getRuleContext(Alias_nameContext.class,0);
		}
		public Database_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_database_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterDatabase_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitDatabase_name(this);
		}
	}

	public final Database_nameContext database_name() throws RecognitionException {
		Database_nameContext _localctx = new Database_nameContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_database_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			alias_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Simple_expressionContext simple_expression() {
			return getRuleContext(Simple_expressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_expression);
		try {
			setState(253);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(240);
				simple_expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(241);
				match(T__3);
				setState(242);
				expression();
				setState(243);
				match(T__4);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(245);
				match(T__19);
				setState(246);
				expression();
				setState(247);
				match(T__20);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(249);
				match(T__21);
				setState(250);
				expression();
				setState(251);
				match(T__22);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Groupby_clauseContext extends ParserRuleContext {
		public Collection_nameContext collection_name() {
			return getRuleContext(Collection_nameContext.class,0);
		}
		public Groupby_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupby_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterGroupby_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitGroupby_clause(this);
		}
	}

	public final Groupby_clauseContext groupby_clause() throws RecognitionException {
		Groupby_clauseContext _localctx = new Groupby_clauseContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_groupby_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			collection_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Summary_expression_and_aliasContext extends ParserRuleContext {
		public Summary_expressionContext summary_expression() {
			return getRuleContext(Summary_expressionContext.class,0);
		}
		public Alias_nameContext alias_name() {
			return getRuleContext(Alias_nameContext.class,0);
		}
		public Summary_expression_and_aliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_summary_expression_and_alias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterSummary_expression_and_alias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitSummary_expression_and_alias(this);
		}
	}

	public final Summary_expression_and_aliasContext summary_expression_and_alias() throws RecognitionException {
		Summary_expression_and_aliasContext _localctx = new Summary_expression_and_aliasContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_summary_expression_and_alias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			summary_expression();
			setState(258);
			match(T__23);
			setState(259);
			alias_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Summary_expressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Summary_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_summary_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterSummary_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitSummary_expression(this);
		}
	}

	public final Summary_expressionContext summary_expression() throws RecognitionException {
		Summary_expressionContext _localctx = new Summary_expressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_summary_expression);
		try {
			setState(270);
			switch (_input.LA(1)) {
			case T__24:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(261);
				match(T__24);
				setState(262);
				expression();
				setState(263);
				match(T__4);
				}
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(265);
				match(T__25);
				setState(266);
				expression();
				setState(267);
				match(T__4);
				}
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(269);
				match(T__26);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Matching_clauseContext extends ParserRuleContext {
		public List<Collection_nameContext> collection_name() {
			return getRuleContexts(Collection_nameContext.class);
		}
		public Collection_nameContext collection_name(int i) {
			return getRuleContext(Collection_nameContext.class,i);
		}
		public Matching_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matching_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterMatching_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitMatching_clause(this);
		}
	}

	public final Matching_clauseContext matching_clause() throws RecognitionException {
		Matching_clauseContext _localctx = new Matching_clauseContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_matching_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			collection_name();
			setState(273);
			match(T__6);
			setState(274);
			collection_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Valid_id_charContext extends ParserRuleContext {
		public Valid_id_charContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valid_id_char; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterValid_id_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitValid_id_char(this);
		}
	}

	public final Valid_id_charContext valid_id_char() throws RecognitionException {
		Valid_id_charContext _localctx = new Valid_id_charContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_valid_id_char);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			_la = _input.LA(1);
			if ( !(_la==T__27 || _la==T__28) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Valid_file_path_charContext extends ParserRuleContext {
		public Valid_id_charContext valid_id_char() {
			return getRuleContext(Valid_id_charContext.class,0);
		}
		public Valid_file_path_charContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valid_file_path_char; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterValid_file_path_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitValid_file_path_char(this);
		}
	}

	public final Valid_file_path_charContext valid_file_path_char() throws RecognitionException {
		Valid_file_path_charContext _localctx = new Valid_file_path_charContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_valid_file_path_char);
		try {
			setState(281);
			switch (_input.LA(1)) {
			case T__27:
			case T__28:
				enterOuterAlt(_localctx, 1);
				{
				setState(278);
				valid_id_char();
				}
				break;
			case T__29:
				enterOuterAlt(_localctx, 2);
				{
				setState(279);
				match(T__29);
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 3);
				{
				setState(280);
				match(T__15);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Simple_expressionContext extends ParserRuleContext {
		public String_expressionContext string_expression() {
			return getRuleContext(String_expressionContext.class,0);
		}
		public Boolean_expressionContext boolean_expression() {
			return getRuleContext(Boolean_expressionContext.class,0);
		}
		public Numeric_expressionContext numeric_expression() {
			return getRuleContext(Numeric_expressionContext.class,0);
		}
		public Simple_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterSimple_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitSimple_expression(this);
		}
	}

	public final Simple_expressionContext simple_expression() throws RecognitionException {
		Simple_expressionContext _localctx = new Simple_expressionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_simple_expression);
		try {
			setState(286);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(283);
				string_expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(284);
				boolean_expression(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(285);
				numeric_expression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class String_expressionContext extends ParserRuleContext {
		public List<String_literalContext> string_literal() {
			return getRuleContexts(String_literalContext.class);
		}
		public String_literalContext string_literal(int i) {
			return getRuleContext(String_literalContext.class,i);
		}
		public Collection_nameContext collection_name() {
			return getRuleContext(Collection_nameContext.class,0);
		}
		public String_operatorContext string_operator() {
			return getRuleContext(String_operatorContext.class,0);
		}
		public String_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterString_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitString_expression(this);
		}
	}

	public final String_expressionContext string_expression() throws RecognitionException {
		String_expressionContext _localctx = new String_expressionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_string_expression);
		try {
			setState(294);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(288);
				string_literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(289);
				collection_name();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(290);
				string_literal();
				setState(291);
				string_operator();
				setState(292);
				string_literal();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Boolean_expressionContext extends ParserRuleContext {
		public List<Boolean_expressionContext> boolean_expression() {
			return getRuleContexts(Boolean_expressionContext.class);
		}
		public Boolean_expressionContext boolean_expression(int i) {
			return getRuleContext(Boolean_expressionContext.class,i);
		}
		public Boolean_constantContext boolean_constant() {
			return getRuleContext(Boolean_constantContext.class,0);
		}
		public Collection_nameContext collection_name() {
			return getRuleContext(Collection_nameContext.class,0);
		}
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public Boolean_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterBoolean_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitBoolean_expression(this);
		}
	}

	public final Boolean_expressionContext boolean_expression() throws RecognitionException {
		return boolean_expression(0);
	}

	private Boolean_expressionContext boolean_expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Boolean_expressionContext _localctx = new Boolean_expressionContext(_ctx, _parentState);
		Boolean_expressionContext _prevctx = _localctx;
		int _startState = 52;
		enterRecursionRule(_localctx, 52, RULE_boolean_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(297);
				match(T__31);
				setState(298);
				boolean_expression(4);
				}
				break;
			case 2:
				{
				setState(299);
				boolean_constant();
				}
				break;
			case 3:
				{
				setState(300);
				collection_name();
				}
				break;
			case 4:
				{
				setState(301);
				match(T__3);
				setState(302);
				boolean_expression(0);
				setState(303);
				match(T__4);
				}
				break;
			case 5:
				{
				setState(305);
				comparison();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(319);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(317);
					switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
					case 1:
						{
						_localctx = new Boolean_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_boolean_expression);
						setState(308);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(309);
						match(T__12);
						setState(310);
						boolean_expression(7);
						}
						break;
					case 2:
						{
						_localctx = new Boolean_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_boolean_expression);
						setState(311);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(312);
						match(T__30);
						setState(313);
						boolean_expression(6);
						}
						break;
					case 3:
						{
						_localctx = new Boolean_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_boolean_expression);
						setState(314);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(315);
						match(T__32);
						setState(316);
						boolean_expression(3);
						}
						break;
					}
					} 
				}
				setState(321);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Boolean_constantContext extends ParserRuleContext {
		public Boolean_constantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterBoolean_constant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitBoolean_constant(this);
		}
	}

	public final Boolean_constantContext boolean_constant() throws RecognitionException {
		Boolean_constantContext _localctx = new Boolean_constantContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_boolean_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322);
			_la = _input.LA(1);
			if ( !(_la==T__33 || _la==T__34) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComparisonContext extends ParserRuleContext {
		public List<Numeric_expressionContext> numeric_expression() {
			return getRuleContexts(Numeric_expressionContext.class);
		}
		public Numeric_expressionContext numeric_expression(int i) {
			return getRuleContext(Numeric_expressionContext.class,i);
		}
		public Comparison_operatorContext comparison_operator() {
			return getRuleContext(Comparison_operatorContext.class,0);
		}
		public List<String_expressionContext> string_expression() {
			return getRuleContexts(String_expressionContext.class);
		}
		public String_expressionContext string_expression(int i) {
			return getRuleContext(String_expressionContext.class,i);
		}
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitComparison(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_comparison);
		try {
			setState(332);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(324);
				numeric_expression(0);
				setState(325);
				comparison_operator();
				setState(326);
				numeric_expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(328);
				string_expression();
				setState(329);
				comparison_operator();
				setState(330);
				string_expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Numeric_expressionContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public Collection_nameContext collection_name() {
			return getRuleContext(Collection_nameContext.class,0);
		}
		public List<Numeric_expressionContext> numeric_expression() {
			return getRuleContexts(Numeric_expressionContext.class);
		}
		public Numeric_expressionContext numeric_expression(int i) {
			return getRuleContext(Numeric_expressionContext.class,i);
		}
		public Numeric_operatorContext numeric_operator() {
			return getRuleContext(Numeric_operatorContext.class,0);
		}
		public Numeric_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterNumeric_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitNumeric_expression(this);
		}
	}

	public final Numeric_expressionContext numeric_expression() throws RecognitionException {
		return numeric_expression(0);
	}

	private Numeric_expressionContext numeric_expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Numeric_expressionContext _localctx = new Numeric_expressionContext(_ctx, _parentState);
		Numeric_expressionContext _prevctx = _localctx;
		int _startState = 58;
		enterRecursionRule(_localctx, 58, RULE_numeric_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(337);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(335);
				number();
				}
				break;
			case 2:
				{
				setState(336);
				collection_name();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(345);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Numeric_expressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_numeric_expression);
					setState(339);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(340);
					numeric_operator();
					setState(341);
					numeric_expression(2);
					}
					} 
				}
				setState(347);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Comparison_operatorContext extends ParserRuleContext {
		public Comparison_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterComparison_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitComparison_operator(this);
		}
	}

	public final Comparison_operatorContext comparison_operator() throws RecognitionException {
		Comparison_operatorContext _localctx = new Comparison_operatorContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_comparison_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(348);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__32) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Numeric_operatorContext extends ParserRuleContext {
		public Numeric_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterNumeric_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitNumeric_operator(this);
		}
	}

	public final Numeric_operatorContext numeric_operator() throws RecognitionException {
		Numeric_operatorContext _localctx = new Numeric_operatorContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_numeric_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__27) | (1L << T__29) | (1L << T__39))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class String_operatorContext extends ParserRuleContext {
		public String_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterString_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitString_operator(this);
		}
	}

	public final String_operatorContext string_operator() throws RecognitionException {
		String_operatorContext _localctx = new String_operatorContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_string_operator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(352);
			match(T__39);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class String_literalContext extends ParserRuleContext {
		public String_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterString_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitString_literal(this);
		}
	}

	public final String_literalContext string_literal() throws RecognitionException {
		String_literalContext _localctx = new String_literalContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_string_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354);
			match(T__40);
			setState(362);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 1)) & ~0x3f) == 0 && ((1L << (_la - 1)) & ((1L << (T__0 - 1)) | (1L << (T__1 - 1)) | (1L << (T__2 - 1)) | (1L << (T__3 - 1)) | (1L << (T__4 - 1)) | (1L << (T__5 - 1)) | (1L << (T__6 - 1)) | (1L << (T__7 - 1)) | (1L << (T__8 - 1)) | (1L << (T__9 - 1)) | (1L << (T__10 - 1)) | (1L << (T__11 - 1)) | (1L << (T__12 - 1)) | (1L << (T__13 - 1)) | (1L << (T__14 - 1)) | (1L << (T__15 - 1)) | (1L << (T__16 - 1)) | (1L << (T__17 - 1)) | (1L << (T__18 - 1)) | (1L << (T__19 - 1)) | (1L << (T__20 - 1)) | (1L << (T__21 - 1)) | (1L << (T__22 - 1)) | (1L << (T__23 - 1)) | (1L << (T__24 - 1)) | (1L << (T__25 - 1)) | (1L << (T__26 - 1)) | (1L << (T__27 - 1)) | (1L << (T__28 - 1)) | (1L << (T__29 - 1)) | (1L << (T__30 - 1)) | (1L << (T__31 - 1)) | (1L << (T__32 - 1)) | (1L << (T__33 - 1)) | (1L << (T__34 - 1)) | (1L << (T__35 - 1)) | (1L << (T__36 - 1)) | (1L << (T__37 - 1)) | (1L << (T__38 - 1)) | (1L << (T__39 - 1)) | (1L << (T__41 - 1)) | (1L << (T__42 - 1)) | (1L << (T__43 - 1)) | (1L << (T__44 - 1)) | (1L << (T__45 - 1)) | (1L << (LETTER - 1)) | (1L << (DIGIT - 1)) | (1L << (WS - 1)) | (1L << (LOAD - 1)) | (1L << (TO - 1)) | (1L << (SHOW - 1)) | (1L << (COLUMNS - 1)) | (1L << (SUMMARIZE - 1)) | (1L << (FILTER - 1)) | (1L << (KEYS - 1)) | (1L << (JOIN - 1)) | (1L << (MATCH - 1)) | (1L << (TRUE - 1)) | (1L << (FALSE - 1)) | (1L << (AND - 1)) | (1L << (OR - 1)) | (1L << (NOT - 1)) | (1L << (AS - 1)))) != 0)) {
				{
				setState(360);
				switch (_input.LA(1)) {
				case T__41:
					{
					setState(355);
					match(T__41);
					setState(357);
					switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
					case 1:
						{
						setState(356);
						match(T__40);
						}
						break;
					}
					}
					break;
				case T__0:
				case T__1:
				case T__2:
				case T__3:
				case T__4:
				case T__5:
				case T__6:
				case T__7:
				case T__8:
				case T__9:
				case T__10:
				case T__11:
				case T__12:
				case T__13:
				case T__14:
				case T__15:
				case T__16:
				case T__17:
				case T__18:
				case T__19:
				case T__20:
				case T__21:
				case T__22:
				case T__23:
				case T__24:
				case T__25:
				case T__26:
				case T__27:
				case T__28:
				case T__29:
				case T__30:
				case T__31:
				case T__32:
				case T__33:
				case T__34:
				case T__35:
				case T__36:
				case T__37:
				case T__38:
				case T__39:
				case T__42:
				case T__43:
				case T__44:
				case T__45:
				case LETTER:
				case DIGIT:
				case WS:
				case LOAD:
				case TO:
				case SHOW:
				case COLUMNS:
				case SUMMARIZE:
				case FILTER:
				case KEYS:
				case JOIN:
				case MATCH:
				case TRUE:
				case FALSE:
				case AND:
				case OR:
				case NOT:
				case AS:
					{
					setState(359);
					_la = _input.LA(1);
					if ( _la <= 0 || (_la==T__40 || _la==T__41) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(364);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(365);
			match(T__40);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Brackets_expressionContext extends ParserRuleContext {
		public Expression_listContext expression_list() {
			return getRuleContext(Expression_listContext.class,0);
		}
		public Brackets_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_brackets_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterBrackets_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitBrackets_expression(this);
		}
	}

	public final Brackets_expressionContext brackets_expression() throws RecognitionException {
		Brackets_expressionContext _localctx = new Brackets_expressionContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_brackets_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(367);
			match(T__19);
			setState(368);
			expression_list();
			setState(369);
			match(T__20);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Braces_expressionContext extends ParserRuleContext {
		public Expression_listContext expression_list() {
			return getRuleContext(Expression_listContext.class,0);
		}
		public Braces_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_braces_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterBraces_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitBraces_expression(this);
		}
	}

	public final Braces_expressionContext braces_expression() throws RecognitionException {
		Braces_expressionContext _localctx = new Braces_expressionContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_braces_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(371);
			match(T__21);
			setState(372);
			expression_list();
			setState(373);
			match(T__22);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Numeric_operationContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public Numeric_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterNumeric_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitNumeric_operation(this);
		}
	}

	public final Numeric_operationContext numeric_operation() throws RecognitionException {
		Numeric_operationContext _localctx = new Numeric_operationContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_numeric_operation);
		try {
			setState(396);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(375);
				number();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(376);
				match(T__3);
				setState(377);
				expression();
				setState(378);
				match(T__4);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(380);
				expression();
				setState(381);
				match(T__39);
				setState(382);
				expression();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(384);
				expression();
				setState(385);
				match(T__29);
				setState(386);
				expression();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(388);
				expression();
				setState(389);
				match(T__27);
				setState(390);
				expression();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(392);
				expression();
				setState(393);
				match(T__17);
				setState(394);
				expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class String_operationContext extends ParserRuleContext {
		public String_literalContext string_literal() {
			return getRuleContext(String_literalContext.class,0);
		}
		public Collection_nameContext collection_name() {
			return getRuleContext(Collection_nameContext.class,0);
		}
		public List<String_operationContext> string_operation() {
			return getRuleContexts(String_operationContext.class);
		}
		public String_operationContext string_operation(int i) {
			return getRuleContext(String_operationContext.class,i);
		}
		public String_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterString_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitString_operation(this);
		}
	}

	public final String_operationContext string_operation() throws RecognitionException {
		return string_operation(0);
	}

	private String_operationContext string_operation(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		String_operationContext _localctx = new String_operationContext(_ctx, _parentState);
		String_operationContext _prevctx = _localctx;
		int _startState = 74;
		enterRecursionRule(_localctx, 74, RULE_string_operation, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(401);
			switch (_input.LA(1)) {
			case T__40:
				{
				setState(399);
				string_literal();
				}
				break;
			case T__27:
			case T__28:
			case LETTER:
			case DIGIT:
				{
				setState(400);
				collection_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(408);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new String_operationContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_string_operation);
					setState(403);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(404);
					match(T__39);
					setState(405);
					string_operation(2);
					}
					} 
				}
				setState(410);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public List<TerminalNode> DIGIT() { return getTokens(CommonAnalyticsParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(CommonAnalyticsParser.DIGIT, i);
		}
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_number);
		int _la;
		try {
			int _alt;
			setState(427);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(412); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(411);
						match(DIGIT);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(414); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(417); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(416);
					match(DIGIT);
					}
					}
					setState(419); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DIGIT );
				setState(421);
				match(T__15);
				setState(423); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(422);
						match(DIGIT);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(425); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Structure_definition_listContext extends ParserRuleContext {
		public Structure_definition_fieldContext structure_definition_field() {
			return getRuleContext(Structure_definition_fieldContext.class,0);
		}
		public List<Structure_definition_listContext> structure_definition_list() {
			return getRuleContexts(Structure_definition_listContext.class);
		}
		public Structure_definition_listContext structure_definition_list(int i) {
			return getRuleContext(Structure_definition_listContext.class,i);
		}
		public Structure_definition_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structure_definition_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterStructure_definition_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitStructure_definition_list(this);
		}
	}

	public final Structure_definition_listContext structure_definition_list() throws RecognitionException {
		Structure_definition_listContext _localctx = new Structure_definition_listContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_structure_definition_list);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			structure_definition_field();
			setState(434);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(430);
					match(T__18);
					setState(431);
					structure_definition_list();
					}
					} 
				}
				setState(436);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Structure_definition_fieldContext extends ParserRuleContext {
		public Alias_nameContext alias_name() {
			return getRuleContext(Alias_nameContext.class,0);
		}
		public Data_typeContext data_type() {
			return getRuleContext(Data_typeContext.class,0);
		}
		public Structure_definition_fieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structure_definition_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterStructure_definition_field(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitStructure_definition_field(this);
		}
	}

	public final Structure_definition_fieldContext structure_definition_field() throws RecognitionException {
		Structure_definition_fieldContext _localctx = new Structure_definition_fieldContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_structure_definition_field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(437);
			alias_name();
			setState(438);
			match(T__42);
			setState(439);
			data_type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Data_typeContext extends ParserRuleContext {
		public Data_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).enterData_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommonAnalyticsListener ) ((CommonAnalyticsListener)listener).exitData_type(this);
		}
	}

	public final Data_typeContext data_type() throws RecognitionException {
		Data_typeContext _localctx = new Data_typeContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_data_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__43) | (1L << T__44) | (1L << T__45))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 26:
			return boolean_expression_sempred((Boolean_expressionContext)_localctx, predIndex);
		case 29:
			return numeric_expression_sempred((Numeric_expressionContext)_localctx, predIndex);
		case 37:
			return string_operation_sempred((String_operationContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean boolean_expression_sempred(Boolean_expressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean numeric_expression_sempred(Numeric_expressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean string_operation_sempred(String_operationContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3B\u01be\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\3"+
		"\2\7\2X\n\2\f\2\16\2[\13\2\3\3\3\3\3\3\6\3`\n\3\r\3\16\3a\3\4\3\4\3\4"+
		"\3\4\3\4\5\4i\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0083\n\7\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u008e\n\b\3\b\3\b\3\b\3\b\5\b\u0094\n\b\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00a6"+
		"\n\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\5\13\u00b0\n\13\3\13\3\13\3\f"+
		"\3\f\3\r\3\r\3\r\3\r\3\r\7\r\u00bb\n\r\f\r\16\r\u00be\13\r\5\r\u00c0\n"+
		"\r\5\r\u00c2\n\r\3\16\3\16\3\16\3\16\7\16\u00c8\n\16\f\16\16\16\u00cb"+
		"\13\16\5\16\u00cd\n\16\3\17\3\17\3\17\3\17\3\17\7\17\u00d4\n\17\f\17\16"+
		"\17\u00d7\13\17\5\17\u00d9\n\17\3\20\3\20\3\20\3\20\7\20\u00df\n\20\f"+
		"\20\16\20\u00e2\13\20\5\20\u00e4\n\20\3\21\3\21\3\21\3\21\7\21\u00ea\n"+
		"\21\f\21\16\21\u00ed\13\21\5\21\u00ef\n\21\3\22\3\22\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u0100\n\23\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\5\26\u0111\n\26\3\27\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\31\5\31\u011c"+
		"\n\31\3\32\3\32\3\32\5\32\u0121\n\32\3\33\3\33\3\33\3\33\3\33\3\33\5\33"+
		"\u0129\n\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u0135"+
		"\n\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u0140\n\34\f\34"+
		"\16\34\u0143\13\34\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5"+
		"\36\u014f\n\36\3\37\3\37\3\37\5\37\u0154\n\37\3\37\3\37\3\37\3\37\7\37"+
		"\u015a\n\37\f\37\16\37\u015d\13\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3#\5#\u0168"+
		"\n#\3#\7#\u016b\n#\f#\16#\u016e\13#\3#\3#\3$\3$\3$\3$\3%\3%\3%\3%\3&\3"+
		"&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\5&\u018f\n"+
		"&\3\'\3\'\3\'\5\'\u0194\n\'\3\'\3\'\3\'\7\'\u0199\n\'\f\'\16\'\u019c\13"+
		"\'\3(\6(\u019f\n(\r(\16(\u01a0\3(\6(\u01a4\n(\r(\16(\u01a5\3(\3(\6(\u01aa"+
		"\n(\r(\16(\u01ab\5(\u01ae\n(\3)\3)\3)\7)\u01b3\n)\f)\16)\u01b6\13)\3*"+
		"\3*\3*\3*\3+\3+\3+\2\5\66<L,\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"$&(*,.\60\62\64\668:<>@BDFHJLNPRT\2\b\3\2\36\37\3\2$%\4\2##&)\6\2\24\24"+
		"\36\36  **\3\2+,\3\2.\60\u01d0\2Y\3\2\2\2\4_\3\2\2\2\6h\3\2\2\2\bj\3\2"+
		"\2\2\nt\3\2\2\2\fx\3\2\2\2\16\u0087\3\2\2\2\20\u0098\3\2\2\2\22\u00aa"+
		"\3\2\2\2\24\u00af\3\2\2\2\26\u00b3\3\2\2\2\30\u00c1\3\2\2\2\32\u00cc\3"+
		"\2\2\2\34\u00d8\3\2\2\2\36\u00e3\3\2\2\2 \u00ee\3\2\2\2\"\u00f0\3\2\2"+
		"\2$\u00ff\3\2\2\2&\u0101\3\2\2\2(\u0103\3\2\2\2*\u0110\3\2\2\2,\u0112"+
		"\3\2\2\2.\u0116\3\2\2\2\60\u011b\3\2\2\2\62\u0120\3\2\2\2\64\u0128\3\2"+
		"\2\2\66\u0134\3\2\2\28\u0144\3\2\2\2:\u014e\3\2\2\2<\u0153\3\2\2\2>\u015e"+
		"\3\2\2\2@\u0160\3\2\2\2B\u0162\3\2\2\2D\u0164\3\2\2\2F\u0171\3\2\2\2H"+
		"\u0175\3\2\2\2J\u018e\3\2\2\2L\u0193\3\2\2\2N\u01ad\3\2\2\2P\u01af\3\2"+
		"\2\2R\u01b7\3\2\2\2T\u01bb\3\2\2\2VX\5\6\4\2WV\3\2\2\2X[\3\2\2\2YW\3\2"+
		"\2\2YZ\3\2\2\2Z\3\3\2\2\2[Y\3\2\2\2\\`\7\61\2\2]`\7\62\2\2^`\5.\30\2_"+
		"\\\3\2\2\2_]\3\2\2\2_^\3\2\2\2`a\3\2\2\2a_\3\2\2\2ab\3\2\2\2b\5\3\2\2"+
		"\2ci\5\b\5\2di\5\n\6\2ei\5\f\7\2fi\5\20\t\2gi\5\16\b\2hc\3\2\2\2hd\3\2"+
		"\2\2he\3\2\2\2hf\3\2\2\2hg\3\2\2\2i\7\3\2\2\2jk\7\3\2\2kl\5\22\n\2lm\7"+
		"\4\2\2mn\5\24\13\2no\7\5\2\2op\7\6\2\2pq\5P)\2qr\7\7\2\2rs\5\26\f\2s\t"+
		"\3\2\2\2tu\7\b\2\2uv\5\24\13\2vw\5\26\f\2w\13\3\2\2\2xy\5\24\13\2yz\7"+
		"\t\2\2z{\7\n\2\2{|\5\24\13\2|}\7\6\2\2}~\5\30\r\2~\177\7\7\2\2\177\u0080"+
		"\7\13\2\2\u0080\u0082\7\6\2\2\u0081\u0083\5\32\16\2\u0082\u0081\3\2\2"+
		"\2\u0082\u0083\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085\7\7\2\2\u0085\u0086"+
		"\5\26\f\2\u0086\r\3\2\2\2\u0087\u0088\5\24\13\2\u0088\u0089\7\t\2\2\u0089"+
		"\u008a\7\f\2\2\u008a\u008b\5\24\13\2\u008b\u008d\7\6\2\2\u008c\u008e\5"+
		"\36\20\2\u008d\u008c\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f\3\2\2\2\u008f"+
		"\u0090\7\7\2\2\u0090\u0091\7\r\2\2\u0091\u0093\7\6\2\2\u0092\u0094\5\34"+
		"\17\2\u0093\u0092\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095"+
		"\u0096\7\7\2\2\u0096\u0097\5\26\f\2\u0097\17\3\2\2\2\u0098\u0099\5\24"+
		"\13\2\u0099\u009a\7\t\2\2\u009a\u009b\7\16\2\2\u009b\u009c\5\24\13\2\u009c"+
		"\u009d\7\17\2\2\u009d\u009e\5\24\13\2\u009e\u009f\7\20\2\2\u009f\u00a0"+
		"\7\6\2\2\u00a0\u00a1\5\30\r\2\u00a1\u00a2\7\7\2\2\u00a2\u00a3\7\21\2\2"+
		"\u00a3\u00a5\7\6\2\2\u00a4\u00a6\5 \21\2\u00a5\u00a4\3\2\2\2\u00a5\u00a6"+
		"\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\7\7\2\2\u00a8\u00a9\5\26\f\2"+
		"\u00a9\21\3\2\2\2\u00aa\u00ab\5D#\2\u00ab\23\3\2\2\2\u00ac\u00ad\5\"\22"+
		"\2\u00ad\u00ae\7\22\2\2\u00ae\u00b0\3\2\2\2\u00af\u00ac\3\2\2\2\u00af"+
		"\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\5\4\3\2\u00b2\25\3\2\2"+
		"\2\u00b3\u00b4\7\23\2\2\u00b4\27\3\2\2\2\u00b5\u00c2\7\24\2\2\u00b6\u00c0"+
		"\5$\23\2\u00b7\u00bc\5$\23\2\u00b8\u00b9\7\25\2\2\u00b9\u00bb\5\30\r\2"+
		"\u00ba\u00b8\3\2\2\2\u00bb\u00be\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd"+
		"\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00bf\u00b6\3\2\2\2\u00bf"+
		"\u00b7\3\2\2\2\u00c0\u00c2\3\2\2\2\u00c1\u00b5\3\2\2\2\u00c1\u00bf\3\2"+
		"\2\2\u00c2\31\3\2\2\2\u00c3\u00cd\5\66\34\2\u00c4\u00c9\5\66\34\2\u00c5"+
		"\u00c6\7\25\2\2\u00c6\u00c8\5\32\16\2\u00c7\u00c5\3\2\2\2\u00c8\u00cb"+
		"\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cd\3\2\2\2\u00cb"+
		"\u00c9\3\2\2\2\u00cc\u00c3\3\2\2\2\u00cc\u00c4\3\2\2\2\u00cd\33\3\2\2"+
		"\2\u00ce\u00d9\7\24\2\2\u00cf\u00d9\5&\24\2\u00d0\u00d5\5&\24\2\u00d1"+
		"\u00d2\7\25\2\2\u00d2\u00d4\5\34\17\2\u00d3\u00d1\3\2\2\2\u00d4\u00d7"+
		"\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7"+
		"\u00d5\3\2\2\2\u00d8\u00ce\3\2\2\2\u00d8\u00cf\3\2\2\2\u00d8\u00d0\3\2"+
		"\2\2\u00d9\35\3\2\2\2\u00da\u00e4\5(\25\2\u00db\u00e0\5(\25\2\u00dc\u00dd"+
		"\7\25\2\2\u00dd\u00df\5\36\20\2\u00de\u00dc\3\2\2\2\u00df\u00e2\3\2\2"+
		"\2\u00e0\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e4\3\2\2\2\u00e2\u00e0"+
		"\3\2\2\2\u00e3\u00da\3\2\2\2\u00e3\u00db\3\2\2\2\u00e4\37\3\2\2\2\u00e5"+
		"\u00ef\5,\27\2\u00e6\u00eb\5,\27\2\u00e7\u00e8\7\25\2\2\u00e8\u00ea\5"+
		" \21\2\u00e9\u00e7\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb"+
		"\u00ec\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00e5\3\2"+
		"\2\2\u00ee\u00e6\3\2\2\2\u00ef!\3\2\2\2\u00f0\u00f1\5\4\3\2\u00f1#\3\2"+
		"\2\2\u00f2\u0100\5\62\32\2\u00f3\u00f4\7\6\2\2\u00f4\u00f5\5$\23\2\u00f5"+
		"\u00f6\7\7\2\2\u00f6\u0100\3\2\2\2\u00f7\u00f8\7\26\2\2\u00f8\u00f9\5"+
		"$\23\2\u00f9\u00fa\7\27\2\2\u00fa\u0100\3\2\2\2\u00fb\u00fc\7\30\2\2\u00fc"+
		"\u00fd\5$\23\2\u00fd\u00fe\7\31\2\2\u00fe\u0100\3\2\2\2\u00ff\u00f2\3"+
		"\2\2\2\u00ff\u00f3\3\2\2\2\u00ff\u00f7\3\2\2\2\u00ff\u00fb\3\2\2\2\u0100"+
		"%\3\2\2\2\u0101\u0102\5\24\13\2\u0102\'\3\2\2\2\u0103\u0104\5*\26\2\u0104"+
		"\u0105\7\32\2\2\u0105\u0106\5\4\3\2\u0106)\3\2\2\2\u0107\u0108\7\33\2"+
		"\2\u0108\u0109\5$\23\2\u0109\u010a\7\7\2\2\u010a\u0111\3\2\2\2\u010b\u010c"+
		"\7\34\2\2\u010c\u010d\5$\23\2\u010d\u010e\7\7\2\2\u010e\u0111\3\2\2\2"+
		"\u010f\u0111\7\35\2\2\u0110\u0107\3\2\2\2\u0110\u010b\3\2\2\2\u0110\u010f"+
		"\3\2\2\2\u0111+\3\2\2\2\u0112\u0113\5\24\13\2\u0113\u0114\7\t\2\2\u0114"+
		"\u0115\5\24\13\2\u0115-\3\2\2\2\u0116\u0117\t\2\2\2\u0117/\3\2\2\2\u0118"+
		"\u011c\5.\30\2\u0119\u011c\7 \2\2\u011a\u011c\7\22\2\2\u011b\u0118\3\2"+
		"\2\2\u011b\u0119\3\2\2\2\u011b\u011a\3\2\2\2\u011c\61\3\2\2\2\u011d\u0121"+
		"\5\64\33\2\u011e\u0121\5\66\34\2\u011f\u0121\5<\37\2\u0120\u011d\3\2\2"+
		"\2\u0120\u011e\3\2\2\2\u0120\u011f\3\2\2\2\u0121\63\3\2\2\2\u0122\u0129"+
		"\5D#\2\u0123\u0129\5\24\13\2\u0124\u0125\5D#\2\u0125\u0126\5B\"\2\u0126"+
		"\u0127\5D#\2\u0127\u0129\3\2\2\2\u0128\u0122\3\2\2\2\u0128\u0123\3\2\2"+
		"\2\u0128\u0124\3\2\2\2\u0129\65\3\2\2\2\u012a\u012b\b\34\1\2\u012b\u012c"+
		"\7\"\2\2\u012c\u0135\5\66\34\6\u012d\u0135\58\35\2\u012e\u0135\5\24\13"+
		"\2\u012f\u0130\7\6\2\2\u0130\u0131\5\66\34\2\u0131\u0132\7\7\2\2\u0132"+
		"\u0135\3\2\2\2\u0133\u0135\5:\36\2\u0134\u012a\3\2\2\2\u0134\u012d\3\2"+
		"\2\2\u0134\u012e\3\2\2\2\u0134\u012f\3\2\2\2\u0134\u0133\3\2\2\2\u0135"+
		"\u0141\3\2\2\2\u0136\u0137\f\b\2\2\u0137\u0138\7\17\2\2\u0138\u0140\5"+
		"\66\34\t\u0139\u013a\f\7\2\2\u013a\u013b\7!\2\2\u013b\u0140\5\66\34\b"+
		"\u013c\u013d\f\4\2\2\u013d\u013e\7#\2\2\u013e\u0140\5\66\34\5\u013f\u0136"+
		"\3\2\2\2\u013f\u0139\3\2\2\2\u013f\u013c\3\2\2\2\u0140\u0143\3\2\2\2\u0141"+
		"\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142\67\3\2\2\2\u0143\u0141\3\2\2"+
		"\2\u0144\u0145\t\3\2\2\u01459\3\2\2\2\u0146\u0147\5<\37\2\u0147\u0148"+
		"\5> \2\u0148\u0149\5<\37\2\u0149\u014f\3\2\2\2\u014a\u014b\5\64\33\2\u014b"+
		"\u014c\5> \2\u014c\u014d\5\64\33\2\u014d\u014f\3\2\2\2\u014e\u0146\3\2"+
		"\2\2\u014e\u014a\3\2\2\2\u014f;\3\2\2\2\u0150\u0151\b\37\1\2\u0151\u0154"+
		"\5N(\2\u0152\u0154\5\24\13\2\u0153\u0150\3\2\2\2\u0153\u0152\3\2\2\2\u0154"+
		"\u015b\3\2\2\2\u0155\u0156\f\3\2\2\u0156\u0157\5@!\2\u0157\u0158\5<\37"+
		"\4\u0158\u015a\3\2\2\2\u0159\u0155\3\2\2\2\u015a\u015d\3\2\2\2\u015b\u0159"+
		"\3\2\2\2\u015b\u015c\3\2\2\2\u015c=\3\2\2\2\u015d\u015b\3\2\2\2\u015e"+
		"\u015f\t\4\2\2\u015f?\3\2\2\2\u0160\u0161\t\5\2\2\u0161A\3\2\2\2\u0162"+
		"\u0163\7*\2\2\u0163C\3\2\2\2\u0164\u016c\7+\2\2\u0165\u0167\7,\2\2\u0166"+
		"\u0168\7+\2\2\u0167\u0166\3\2\2\2\u0167\u0168\3\2\2\2\u0168\u016b\3\2"+
		"\2\2\u0169\u016b\n\6\2\2\u016a\u0165\3\2\2\2\u016a\u0169\3\2\2\2\u016b"+
		"\u016e\3\2\2\2\u016c\u016a\3\2\2\2\u016c\u016d\3\2\2\2\u016d\u016f\3\2"+
		"\2\2\u016e\u016c\3\2\2\2\u016f\u0170\7+\2\2\u0170E\3\2\2\2\u0171\u0172"+
		"\7\26\2\2\u0172\u0173\5\30\r\2\u0173\u0174\7\27\2\2\u0174G\3\2\2\2\u0175"+
		"\u0176\7\30\2\2\u0176\u0177\5\30\r\2\u0177\u0178\7\31\2\2\u0178I\3\2\2"+
		"\2\u0179\u018f\5N(\2\u017a\u017b\7\6\2\2\u017b\u017c\5$\23\2\u017c\u017d"+
		"\7\7\2\2\u017d\u018f\3\2\2\2\u017e\u017f\5$\23\2\u017f\u0180\7*\2\2\u0180"+
		"\u0181\5$\23\2\u0181\u018f\3\2\2\2\u0182\u0183\5$\23\2\u0183\u0184\7 "+
		"\2\2\u0184\u0185\5$\23\2\u0185\u018f\3\2\2\2\u0186\u0187\5$\23\2\u0187"+
		"\u0188\7\36\2\2\u0188\u0189\5$\23\2\u0189\u018f\3\2\2\2\u018a\u018b\5"+
		"$\23\2\u018b\u018c\7\24\2\2\u018c\u018d\5$\23\2\u018d\u018f\3\2\2\2\u018e"+
		"\u0179\3\2\2\2\u018e\u017a\3\2\2\2\u018e\u017e\3\2\2\2\u018e\u0182\3\2"+
		"\2\2\u018e\u0186\3\2\2\2\u018e\u018a\3\2\2\2\u018fK\3\2\2\2\u0190\u0191"+
		"\b\'\1\2\u0191\u0194\5D#\2\u0192\u0194\5\24\13\2\u0193\u0190\3\2\2\2\u0193"+
		"\u0192\3\2\2\2\u0194\u019a\3\2\2\2\u0195\u0196\f\3\2\2\u0196\u0197\7*"+
		"\2\2\u0197\u0199\5L\'\4\u0198\u0195\3\2\2\2\u0199\u019c\3\2\2\2\u019a"+
		"\u0198\3\2\2\2\u019a\u019b\3\2\2\2\u019bM\3\2\2\2\u019c\u019a\3\2\2\2"+
		"\u019d\u019f\7\62\2\2\u019e\u019d\3\2\2\2\u019f\u01a0\3\2\2\2\u01a0\u019e"+
		"\3\2\2\2\u01a0\u01a1\3\2\2\2\u01a1\u01ae\3\2\2\2\u01a2\u01a4\7\62\2\2"+
		"\u01a3\u01a2\3\2\2\2\u01a4\u01a5\3\2\2\2\u01a5\u01a3\3\2\2\2\u01a5\u01a6"+
		"\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01a9\7\22\2\2\u01a8\u01aa\7\62\2\2"+
		"\u01a9\u01a8\3\2\2\2\u01aa\u01ab\3\2\2\2\u01ab\u01a9\3\2\2\2\u01ab\u01ac"+
		"\3\2\2\2\u01ac\u01ae\3\2\2\2\u01ad\u019e\3\2\2\2\u01ad\u01a3\3\2\2\2\u01ae"+
		"O\3\2\2\2\u01af\u01b4\5R*\2\u01b0\u01b1\7\25\2\2\u01b1\u01b3\5P)\2\u01b2"+
		"\u01b0\3\2\2\2\u01b3\u01b6\3\2\2\2\u01b4\u01b2\3\2\2\2\u01b4\u01b5\3\2"+
		"\2\2\u01b5Q\3\2\2\2\u01b6\u01b4\3\2\2\2\u01b7\u01b8\5\4\3\2\u01b8\u01b9"+
		"\7-\2\2\u01b9\u01ba\5T+\2\u01baS\3\2\2\2\u01bb\u01bc\t\7\2\2\u01bcU\3"+
		"\2\2\2,Y_ah\u0082\u008d\u0093\u00a5\u00af\u00bc\u00bf\u00c1\u00c9\u00cc"+
		"\u00d5\u00d8\u00e0\u00e3\u00eb\u00ee\u00ff\u0110\u011b\u0120\u0128\u0134"+
		"\u013f\u0141\u014e\u0153\u015b\u0167\u016a\u016c\u018e\u0193\u019a\u01a0"+
		"\u01a5\u01ab\u01ad\u01b4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}