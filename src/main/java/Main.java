import com.j9nos.pinhokjsonifier.Json;
import com.j9nos.pinhokjsonifier.Language;
import com.j9nos.pinhokjsonifier.PopulateVocabulary;

public final class Main {

    private Main() {
    }

    public static void main(final String[] args) {
        final Language language = Language.newFrom("mandarin");
        PopulateVocabulary.of(language);
        Json.save(language);
    }

}