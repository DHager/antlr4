/*
 * Copyright (c) 2012-2017 The ANTLR Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

package org.antlr.v4.codegen.target;

import org.antlr.v4.codegen.CodeGenerator;
import org.antlr.v4.codegen.Target;
import org.antlr.v4.codegen.UnicodeEscapes;
import org.antlr.v4.tool.ast.GrammarAST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.StringRenderer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 *
 * @author Darien Hager
 */
public class Php7Target extends Target {
	private static final String[] php7Keywords = {
			"abstract", "and", "array", "as",
			"break",
			"callable", "case", "catch", "class", "clone", "const", "continue",
			"declare", "default", "die", "do",
			"echo", "else", "elseif", "empty", "enddeclare", "endfor", "endforeach", "endif", "endswitch", "endwhile", "eval", "exit", "extends",
			"final", "finally", "for", "foreach", "function",
			"global", "goto",
			"if", "implements", "include", "include_once", "instanceof", "insteadof", "interface", "isset",
			"list",
			"namespace", "new",
			"or",
			"print", "private", "protected", "public",
			"require", "require_once", "return",
			"static", "switch",
			"throw", "trait", "try",
			"unset", "use",
			"var",
			"while",
			"xor",
			"yield",
			"__halt_compiler", "__CLASS__", "__DIR__", "__FILE__", "__FUNCTION__", "__LINE__", "__METHOD__", "__NAMESPACE__", "__TRAIT__"
	};

	/** Avoid grammar symbols in this set to prevent conflicts in gen'd code. */
	private final Set<String> badWords = new HashSet<>();

	public Php7Target(CodeGenerator gen) {
		super(gen, "Php7");
	}

	@Override
	protected boolean visibleGrammarSymbolCausesIssueInGeneratedCode(GrammarAST idNode) {
		return getBadWords().contains(idNode.getText());
	}

	@Override
	protected STGroup loadTemplates() {
		STGroup result = super.loadTemplates();
		result.registerRenderer(String.class, new PhpStringRenderer(), true);
		return result;
	}

	protected static class PhpStringRenderer extends StringRenderer {

		@Override
		public String toString(Object o, String formatString, Locale locale) {
			return super.toString(o, formatString, locale);
		}
	}

	@Override
	public boolean wantsBaseListener() {
		return false; //TODO Figure out how this affects interfaces/traits/baseclass
	}

	@Override
	public boolean wantsBaseVisitor() {
		return false; //TODO Figure out how this affects interfaces/traits/baseclass
	}

	@Override
	public boolean supportsOverloadedMethods() {
		return false;
	}

	@Override
	public String getVersion() {
		return "4.7";
	}

	private Set<String> getBadWords() {
		if (badWords.isEmpty()) {
			addBadWords();
		}

		return badWords;
	}

	private void addBadWords() {
		badWords.addAll(Arrays.asList(php7Keywords));
		badWords.add("rule");
		badWords.add("parserRule");
	}

	@Override
	protected void appendUnicodeEscapedCodePoint(int codePoint, StringBuilder sb) {
		UnicodeEscapes.appendPhpStyleEscapedCodePoint(codePoint, sb);
	}
}
