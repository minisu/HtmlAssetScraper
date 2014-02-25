import org.junit.Test;

import java.io.IOException;

public class AssetScraperTest
{
	@Test
	public void abc() throws IOException
	{
		AssetScraper scraper = new AssetScraper();
		scraper.scrape( "http://www.loadui.org" );
	}
}
