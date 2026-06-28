package tokenizer;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Removes basic stop words
 */
public class StandardTokenization implements TokenStrategy {
    // region Stop Words
    private static final Set<String> STOP_WORDS = Set.of(
            // Articles
            "a", "an", "the",

            // Personal Pronouns
            "i", "me", "my", "mine", "myself",
            "you", "your", "yours", "yourself", "yourselves",
            "he", "him", "his", "himself",
            "she", "her", "hers", "herself",
            "it", "its", "itself",
            "we", "us", "our", "ours", "ourselves",
            "they", "them", "their", "theirs", "themselves",

            // Relative Pronouns
            "who", "whom", "whose", "which", "what",

            // Demonstratives
            "this", "that", "these", "those",

            // Coordinating Conjunctions
            "and", "but", "or", "nor", "for", "yet", "so",

            // Subordinating Conjunctions
            "although", "because", "since", "unless", "until",
            "while", "whereas", "whether", "though", "even",
            "if", "as", "than",

            // Prepositions
            "about", "above", "across", "after", "against", "along",
            "among", "around", "at", "before", "behind", "below",
            "beneath", "beside", "besides", "between", "beyond",
            "by", "despite", "down", "during", "except", "from",
            "in", "inside", "instead", "into", "like", "near",
            "of", "off", "on", "onto", "out", "outside", "over",
            "past", "per", "regarding", "through", "throughout",
            "to", "toward", "towards", "under", "underneath",
            "up", "upon", "via", "with", "within", "without",

            // Auxiliary Verbs
            "am", "is", "are", "was", "were", "be", "been", "being",
            "have", "has", "had", "having",
            "do", "does", "did",
            "will", "would", "shall", "should",
            "may", "might", "must", "can", "could",
            "need", "ought",

            // Negations
            "no", "not", "never", "neither", "none",
            "nobody", "nothing", "nowhere", "hardly", "scarcely", "barely",

            // Adverbs
            "again", "almost", "already", "also", "always",
            "anyway", "away", "back", "else", "enough",
            "especially", "eventually", "exactly", "fairly",
            "far", "finally", "frequently", "further",
            "generally", "here", "however", "indeed",
            "just", "largely", "lately", "likely",
            "mainly", "maybe", "meanwhile", "merely", "mostly",
            "nearly", "nonetheless", "now", "often",
            "once", "only", "otherwise", "perhaps", "possibly",
            "previously", "probably", "quite", "rather", "really",
            "recently", "simply", "slightly", "sometimes", "soon",
            "still", "strongly", "suddenly", "then", "there",
            "therefore", "thus", "together", "too", "truly",
            "typically", "usually", "very", "well",

            // Determiners
            "all", "any", "another", "both", "certain", "each", "either",
            "every", "few", "last", "less", "little", "many",
            "more", "most", "much", "next", "other",
            "own", "same", "several", "some", "such",
            "various", "whatever", "whichever", "whole",

            // Quantifiers
            "half", "lot", "lots", "part", "plenty", "rest", "third",

            // Interrogatives
            "how", "when", "where", "why",

            // Temporal
            "afternoon", "ago", "daily", "early", "ever", "first",
            "former", "formerly", "immediately", "initially",
            "late", "later", "prior", "rarely", "seldom",
            "subsequently", "today", "tomorrow", "yesterday",

            // Contractions
            "aren't", "can't", "couldn't", "didn't", "doesn't", "don't",
            "hadn't", "hasn't", "haven't", "he'd", "he'll", "he's",
            "i'd", "i'll", "i'm", "i've", "isn't", "it'd", "it'll",
            "it's", "let's", "mustn't", "shan't", "she'd", "she'll",
            "she's", "shouldn't", "that's", "there's", "they'd", "they'll",
            "they're", "they've", "wasn't", "we'd", "we'll", "we're",
            "we've", "weren't", "what's", "where's", "who's", "won't",
            "wouldn't", "you'd", "you'll", "you're", "you've",

            // Ordinals
            "second", "fourth", "fifth", "sixth", "seventh",
            "eighth", "ninth", "tenth", "latter",

            // Cardinals
            "zero", "one", "two", "three", "four", "five",
            "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen",
            "sixteen", "seventeen", "eighteen", "nineteen", "twenty",
            "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
            "ninety", "hundred", "thousand", "million", "billion",

            // Misc
            "become", "becomes", "becoming", "elsewhere", "etc",
            "get", "gets", "got", "hence", "hereafter", "hereby",
            "herein", "hereof", "hereto", "herewith", "inc",
            "moreover", "namely", "nevertheless", "notwithstanding",
            "particularly", "respectively", "seem", "seemed",
            "seeming", "seems", "sometime", "somewhere", "thereafter",
            "thereby", "thereof", "therein", "thereupon", "wherever",
            "whereby", "wherein", "whereupon"
    );
    // endregion

    private static final Pattern WHITESPACE = Pattern.compile("\\s+");
    private static final Pattern PUNCTUATION = Pattern.compile("^[^a-z']+|[^a-z']+$");

    @Override
    public List<String> toTokens(String input) {
        if (input == null || input.isBlank()) {
            return List.of();
        }

        String lowerCaseInput = input.toLowerCase();

        return WHITESPACE.splitAsStream(lowerCaseInput)
                .map(token -> PUNCTUATION.matcher(token).replaceAll(""))
                .filter(token -> !STOP_WORDS.contains(token))
                .toList();
    }

}