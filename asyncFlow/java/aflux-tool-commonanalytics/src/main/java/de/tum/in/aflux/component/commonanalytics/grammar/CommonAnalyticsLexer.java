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
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CommonAnalyticsLexer extends Lexer {
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
		T__45=46, LETTER=47, DIGIT=48, WS=49;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
		"T__33", "T__34", "T__35", "T__36", "T__37", "T__38", "T__39", "T__40", 
		"T__41", "T__42", "T__43", "T__44", "T__45", "LETTER", "DIGIT", "WS"
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
		"DIGIT", "WS"
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


	public CommonAnalyticsLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CommonAnalytics.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\63\u0127\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3"+
		"\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 "+
		"\3 \3!\3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3%\3%\3&"+
		"\3&\3\'\3\'\3\'\3(\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3-\3-\3.\3.\3."+
		"\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\3\60\5\60\u011d\n\60\3\61\3\61\3"+
		"\62\6\62\u0122\n\62\r\62\16\62\u0123\3\62\3\62\2\2\63\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K"+
		"\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63\3\2\5\4\2C\\c|\3\2\62;\5\2\13\f\17"+
		"\17\"\"\u0127\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2"+
		"\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2"+
		"S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3"+
		"\2\2\2\2a\3\2\2\2\2c\3\2\2\2\3e\3\2\2\2\5j\3\2\2\2\7m\3\2\2\2\tw\3\2\2"+
		"\2\13y\3\2\2\2\r{\3\2\2\2\17\u0080\3\2\2\2\21\u0082\3\2\2\2\23\u0089\3"+
		"\2\2\2\25\u0090\3\2\2\2\27\u009a\3\2\2\2\31\u009f\3\2\2\2\33\u00a4\3\2"+
		"\2\2\35\u00a8\3\2\2\2\37\u00b0\3\2\2\2!\u00b6\3\2\2\2#\u00b8\3\2\2\2%"+
		"\u00ba\3\2\2\2\'\u00bc\3\2\2\2)\u00be\3\2\2\2+\u00c0\3\2\2\2-\u00c2\3"+
		"\2\2\2/\u00c4\3\2\2\2\61\u00c6\3\2\2\2\63\u00c9\3\2\2\2\65\u00ce\3\2\2"+
		"\2\67\u00d3\3\2\2\29\u00db\3\2\2\2;\u00dd\3\2\2\2=\u00df\3\2\2\2?\u00e1"+
		"\3\2\2\2A\u00e4\3\2\2\2C\u00e8\3\2\2\2E\u00eb\3\2\2\2G\u00f0\3\2\2\2I"+
		"\u00f6\3\2\2\2K\u00f8\3\2\2\2M\u00fa\3\2\2\2O\u00fd\3\2\2\2Q\u0100\3\2"+
		"\2\2S\u0102\3\2\2\2U\u0104\3\2\2\2W\u0106\3\2\2\2Y\u0108\3\2\2\2[\u010c"+
		"\3\2\2\2]\u0113\3\2\2\2_\u011c\3\2\2\2a\u011e\3\2\2\2c\u0121\3\2\2\2e"+
		"f\7N\2\2fg\7Q\2\2gh\7C\2\2hi\7F\2\2i\4\3\2\2\2jk\7V\2\2kl\7Q\2\2l\6\3"+
		"\2\2\2mn\7U\2\2no\7V\2\2op\7T\2\2pq\7W\2\2qr\7E\2\2rs\7V\2\2st\7W\2\2"+
		"tu\7T\2\2uv\7G\2\2v\b\3\2\2\2wx\7*\2\2x\n\3\2\2\2yz\7+\2\2z\f\3\2\2\2"+
		"{|\7U\2\2|}\7J\2\2}~\7Q\2\2~\177\7Y\2\2\177\16\3\2\2\2\u0080\u0081\7?"+
		"\2\2\u0081\20\3\2\2\2\u0082\u0083\7U\2\2\u0083\u0084\7G\2\2\u0084\u0085"+
		"\7N\2\2\u0085\u0086\7G\2\2\u0086\u0087\7E\2\2\u0087\u0088\7V\2\2\u0088"+
		"\22\3\2\2\2\u0089\u008a\7H\2\2\u008a\u008b\7K\2\2\u008b\u008c\7N\2\2\u008c"+
		"\u008d\7V\2\2\u008d\u008e\7G\2\2\u008e\u008f\7T\2\2\u008f\24\3\2\2\2\u0090"+
		"\u0091\7U\2\2\u0091\u0092\7W\2\2\u0092\u0093\7O\2\2\u0093\u0094\7O\2\2"+
		"\u0094\u0095\7C\2\2\u0095\u0096\7T\2\2\u0096\u0097\7K\2\2\u0097\u0098"+
		"\7\\\2\2\u0098\u0099\7G\2\2\u0099\26\3\2\2\2\u009a\u009b\7M\2\2\u009b"+
		"\u009c\7G\2\2\u009c\u009d\7[\2\2\u009d\u009e\7U\2\2\u009e\30\3\2\2\2\u009f"+
		"\u00a0\7L\2\2\u00a0\u00a1\7Q\2\2\u00a1\u00a2\7K\2\2\u00a2\u00a3\7P\2\2"+
		"\u00a3\32\3\2\2\2\u00a4\u00a5\7C\2\2\u00a5\u00a6\7P\2\2\u00a6\u00a7\7"+
		"F\2\2\u00a7\34\3\2\2\2\u00a8\u00a9\7E\2\2\u00a9\u00aa\7Q\2\2\u00aa\u00ab"+
		"\7N\2\2\u00ab\u00ac\7W\2\2\u00ac\u00ad\7O\2\2\u00ad\u00ae\7P\2\2\u00ae"+
		"\u00af\7U\2\2\u00af\36\3\2\2\2\u00b0\u00b1\7O\2\2\u00b1\u00b2\7C\2\2\u00b2"+
		"\u00b3\7V\2\2\u00b3\u00b4\7E\2\2\u00b4\u00b5\7J\2\2\u00b5 \3\2\2\2\u00b6"+
		"\u00b7\7\60\2\2\u00b7\"\3\2\2\2\u00b8\u00b9\7=\2\2\u00b9$\3\2\2\2\u00ba"+
		"\u00bb\7,\2\2\u00bb&\3\2\2\2\u00bc\u00bd\7.\2\2\u00bd(\3\2\2\2\u00be\u00bf"+
		"\7]\2\2\u00bf*\3\2\2\2\u00c0\u00c1\7_\2\2\u00c1,\3\2\2\2\u00c2\u00c3\7"+
		"}\2\2\u00c3.\3\2\2\2\u00c4\u00c5\7\177\2\2\u00c5\60\3\2\2\2\u00c6\u00c7"+
		"\7C\2\2\u00c7\u00c8\7U\2\2\u00c8\62\3\2\2\2\u00c9\u00ca\7U\2\2\u00ca\u00cb"+
		"\7W\2\2\u00cb\u00cc\7O\2\2\u00cc\u00cd\7*\2\2\u00cd\64\3\2\2\2\u00ce\u00cf"+
		"\7C\2\2\u00cf\u00d0\7X\2\2\u00d0\u00d1\7I\2\2\u00d1\u00d2\7*\2\2\u00d2"+
		"\66\3\2\2\2\u00d3\u00d4\7E\2\2\u00d4\u00d5\7Q\2\2\u00d5\u00d6\7W\2\2\u00d6"+
		"\u00d7\7P\2\2\u00d7\u00d8\7V\2\2\u00d8\u00d9\7*\2\2\u00d9\u00da\7+\2\2"+
		"\u00da8\3\2\2\2\u00db\u00dc\7/\2\2\u00dc:\3\2\2\2\u00dd\u00de\7a\2\2\u00de"+
		"<\3\2\2\2\u00df\u00e0\7\61\2\2\u00e0>\3\2\2\2\u00e1\u00e2\7Q\2\2\u00e2"+
		"\u00e3\7T\2\2\u00e3@\3\2\2\2\u00e4\u00e5\7P\2\2\u00e5\u00e6\7Q\2\2\u00e6"+
		"\u00e7\7V\2\2\u00e7B\3\2\2\2\u00e8\u00e9\7?\2\2\u00e9\u00ea\7?\2\2\u00ea"+
		"D\3\2\2\2\u00eb\u00ec\7V\2\2\u00ec\u00ed\7T\2\2\u00ed\u00ee\7W\2\2\u00ee"+
		"\u00ef\7G\2\2\u00efF\3\2\2\2\u00f0\u00f1\7H\2\2\u00f1\u00f2\7C\2\2\u00f2"+
		"\u00f3\7N\2\2\u00f3\u00f4\7U\2\2\u00f4\u00f5\7G\2\2\u00f5H\3\2\2\2\u00f6"+
		"\u00f7\7@\2\2\u00f7J\3\2\2\2\u00f8\u00f9\7>\2\2\u00f9L\3\2\2\2\u00fa\u00fb"+
		"\7@\2\2\u00fb\u00fc\7?\2\2\u00fcN\3\2\2\2\u00fd\u00fe\7>\2\2\u00fe\u00ff"+
		"\7?\2\2\u00ffP\3\2\2\2\u0100\u0101\7-\2\2\u0101R\3\2\2\2\u0102\u0103\7"+
		")\2\2\u0103T\3\2\2\2\u0104\u0105\7&\2\2\u0105V\3\2\2\2\u0106\u0107\7<"+
		"\2\2\u0107X\3\2\2\2\u0108\u0109\7K\2\2\u0109\u010a\7P\2\2\u010a\u010b"+
		"\7V\2\2\u010bZ\3\2\2\2\u010c\u010d\7U\2\2\u010d\u010e\7V\2\2\u010e\u010f"+
		"\7T\2\2\u010f\u0110\7K\2\2\u0110\u0111\7P\2\2\u0111\u0112\7I\2\2\u0112"+
		"\\\3\2\2\2\u0113\u0114\7D\2\2\u0114\u0115\7Q\2\2\u0115\u0116\7Q\2\2\u0116"+
		"\u0117\7N\2\2\u0117\u0118\7G\2\2\u0118\u0119\7C\2\2\u0119\u011a\7P\2\2"+
		"\u011a^\3\2\2\2\u011b\u011d\t\2\2\2\u011c\u011b\3\2\2\2\u011d`\3\2\2\2"+
		"\u011e\u011f\t\3\2\2\u011fb\3\2\2\2\u0120\u0122\t\4\2\2\u0121\u0120\3"+
		"\2\2\2\u0122\u0123\3\2\2\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124"+
		"\u0125\3\2\2\2\u0125\u0126\b\62\2\2\u0126d\3\2\2\2\5\2\u011c\u0123\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}