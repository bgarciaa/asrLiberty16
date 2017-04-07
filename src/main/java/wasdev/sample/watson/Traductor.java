package wasdev.sample.watson;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.Language;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;

public class Traductor
{
	public static String translate(String palabra)
	{
		LanguageTranslator service = new LanguageTranslator();
		service.setUsernameAndPassword("f991bfb5-fc60-4f6e-80c3-c018bfc82779", "VAhhCnN7Lxn0");
		service.setEndPoint("https://gateway.watsonplatform.net/language-translator/api");
		TranslationResult translationResult = service.translate(palabra, Language.SPANISH, Language.ENGLISH).execute();
		String traduccionJSON = translationResult.toString();
		JsonParser parser = new JsonParser();
		JsonObject rootObj = parser.parse(traduccionJSON).getAsJsonObject();
		String wordCount = rootObj.get("word_count").getAsString();
		JsonArray traducciones = rootObj.getAsJsonArray("translations");
		String traduccionPrimera = palabra;
		if(traducciones.size()>0)
			traduccionPrimera = traducciones.get(0).getAsJsonObject().get("translation").getAsString();
		return traduccionPrimera;
		
	}
}