import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class AssetScraper
{
	private final Set<String> alreadySeen = new HashSet<>();

	public void scrape(String url) throws IOException {
		Document doc = Jsoup.connect( url ).get();
		Elements media = doc.select("[src]");
		Elements imports = doc.select("link[href]");

		print( "\nMedia:");
		for (Element src : media) {
			if (src.tagName().equals("img"))
				print(" * %s: <%s>",
						src.tagName(), src.attr( "abs:src" ));
			else
				print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
		}

		print("\nImports:");
		for (Element link : imports) {
			String type = link.attr( "type" );
			if( !type.equals( "application/rss+xml" ) && !type.equals( "application/atom+xml" ) )
			print(" * %s <%s>", link.tagName(),link.attr("abs:href"));
		}
	}

	private void print(String msg, Object... args) {
		String output = String.format( msg, args );
		if( !alreadySeen.contains( output ) )
		{
			System.out.println( output );
			alreadySeen.add( output );
		}
	}
}