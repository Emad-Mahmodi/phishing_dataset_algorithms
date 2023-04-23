
package FinalTest_OurWork_PhishWho;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.surround.query.AndQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

/** Simple command-line based search demo. */
public class SearchFiles {
 
   public static int numTotalHits = 0; 
   public SearchFiles() {}
 
   /** Simple command-line based search demo. */
 public static double[][] main( String queryString ) throws Exception {
     String usage =
     "Usage:\tjava org.apache.lucene.demo.SearchFiles [-index dir] [-field f] [-repeat n] [-queries file] [-query string] [-raw] [-paging hitsPerPage]\n\nSee http://lucene.apache.org/core/4_1_0/demo/ for details.";
    /*if (args.length > 0 && ("-h".equals(args[0]) || "-help".equals(args[0]))) {
      System.out.println(usage);
      System.exit(0);
    }*/

    
    String field = "contents";
    String queries = null;
    int repeat = 0;
    boolean raw = false;
    
//  ----------------------------------------ALi Ahmadian ----------------------------------
//    String index = "C:\\Lucene-Tutorials-master\\index2";

//    ----------------------------------------ALi Ahmadian ----------------------------------
//    String index = "C:\\Lucene-Tutorials-master\\Intrusion_Index";
//    ----------------------------------------Masoud Khosravi ----------------------------------
    String index = "D:\\Khosravi_Index";
	//_____________________________________________________________________________________________My Code __________________________________________
	double[][] NumbrDoc_Scor2 =  new double[2][numTotalHits];
	//_____________________________________________________________________________________________________________________________________________
	

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2regular search@@@@@@@@@@@@@@@22
    // regular search
  
  //wildcard query
//  String queryString = "te*t";
//  String queryString = "r*";

  //fuzzy query
//  String queryString = "roam~2";
  
 //phrase query 
//  String queryString = "\"apache lucene\"~5";

  //boolean search
//  String queryString = "\"networks\" AND \"protocol\"";


  //boosted search
//  String queryString = "computer^10 crime";
  //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    int hitsPerPage = 100;
    
    /*for(int i = 0;i < args.length;i++) {
      if ("-index".equals(args[i])) {
        index = args[i+1];
        i++;
      } else if ("-field".equals(args[i])) {
        field = args[i+1];
        i++;
      } else if ("-queries".equals(args[i])) {
        queries = args[i+1];
        i++;
      } else if ("-query".equals(args[i])) {
        queryString = args[i+1];
        i++;
      } else if ("-repeat".equals(args[i])) {
        repeat = Integer.parseInt(args[i+1]);
        i++;
      } else if ("-raw".equals(args[i])) {
        raw = true;
      } else if ("-paging".equals(args[i])) {
        hitsPerPage = Integer.parseInt(args[i+1]);
        if (hitsPerPage <= 0) {
          System.err.println("There must be at least 1 hit per page.");
          System.exit(1);
        }
        i++;
      }
    }*/
    
    try {
		IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer();

		BufferedReader in = null;
		if (queries != null) {
		  in = Files.newBufferedReader(Paths.get(queries), StandardCharsets.UTF_8);
		} else {
		  in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
		}
		QueryParser parser = new QueryParser(field, analyzer);

		while (true) {
		  if (queries == null && queryString == null) {                        // prompt the user
		    System.out.println("Enter query: ");
		  }

		  String line = queryString != null ? queryString : in.readLine();

		  if (line == null || line.length() == -1) {
		    break;
		  }

		  line = line.trim();
		  if (line.length() == 0) {
		    break;
		  }
		  
		  Query query = parser.parse(line);
		  System.out.println("Searching for: " + query.toString(field));
		        
		  if (repeat > 0) {                           // repeat & time as benchmark
		    Date start = new Date();
		    for (int i = 0; i < repeat; i++) {
		      searcher.search(query, 11);
		    }
		    Date end = new Date();
		    System.out.println("Time: "+(end.getTime()-start.getTime())+"ms");
		  }
		  
		  TopDocs results = searcher.search(query, 5 * hitsPerPage);

		 
		  NumbrDoc_Scor2 = doPagingSearch(in, searcher, query, hitsPerPage, raw, queries == null && queryString == null);

		  if (queryString != null) {
		    break;
		  }
		}
		System.out.println();
		reader.close();
		return NumbrDoc_Scor2 ;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		for (int i = 0; i < NumbrDoc_Scor2.length; i++) {
			NumbrDoc_Scor2[0][i] = i;
			NumbrDoc_Scor2[1][i]=0;
		}
		
		return NumbrDoc_Scor2;
	}
    
    
  }


  public static double[][] doPagingSearch(BufferedReader in, IndexSearcher searcher, Query query, 
                                     int hitsPerPage, boolean raw, boolean interactive) throws IOException {
 
    // Collect enough docs to show 5 pages
    TopDocs results = searcher.search(query, 5 * hitsPerPage);
    ScoreDoc[] hits = results.scoreDocs;
    
    numTotalHits = results.totalHits;
    System.out.println(numTotalHits + " total matching documents");
    //________________________________________________________________________________My Code ____________________________________
	
    double[] number_doc = new double[numTotalHits];  
	double[] Scor = new double[numTotalHits];
	double[][] NumbrDoc_Scor = new double[2][numTotalHits];
	//__________________________________________________________________________________________________________________________

    int start = 0;
    int end = Math.min(numTotalHits, hitsPerPage);
        
    while (true) {
      if (end > hits.length) {
        System.out.println("Only results 1 - " + hits.length +" of " + numTotalHits + " total matching documents collected.");
        System.out.println("Collect more (y/n) ?");
        String line = in.readLine();
        if (line.length() == 0 || line.charAt(0) == 'n') {
          break;
        }

        hits = searcher.search(query, numTotalHits).scoreDocs;
      }
      
      end = Math.min(hits.length, start + hitsPerPage);
      
      for (int i = start; i < end; i++) {
        if (raw) {                              // output raw format
          System.out.println("doc="+hits[i].doc+" score="+hits[i].score);
          continue;
        }

        Document doc = searcher.doc(hits[i].doc);

        String path = doc.get("path");
        if (path != null) {
          System.out.println((i+1) + ". " + path+"    =>   "+hits[i].score);
          //____________________________________________________________________________________My Code______________________________

          number_doc[i] =Integer.parseInt( path.substring(path.lastIndexOf("\\")+1 ,path.lastIndexOf(".txt"))); 
          NumbrDoc_Scor[0][i] = number_doc[i];
          Scor[i] = hits[i].score;
          NumbrDoc_Scor[1][i]=Scor[i];
//          NumbrDoc_Scor[i][i]=
          //_________________________________________________________________________________________________________________________
          
          String title = doc.get("title");
          if (title != null) {
            System.out.println("   Title: " + doc.get("title"));
          }
        } else {
          System.out.println((i+1) + ". " + "No path for this document");
        }
                  
      }
      


      if (!interactive || end == 0) {
        break;
      }

      if (numTotalHits >= end) {
        boolean quit = false;
        while (true) {
          System.out.print("Press ");
          if (start - hitsPerPage >= 0) {
            System.out.print("(p)revious page, ");  
          }
          if (start + hitsPerPage < numTotalHits) {
            System.out.print("(n)ext page, ");
          }
          System.out.println("(q)uit or enter number to jump to a page.");
          
          String line = in.readLine();
          if (line.length() == 0 || line.charAt(0)=='q') {
            quit = true;
            break;
          }
          if (line.charAt(0) == 'p') {
            start = Math.max(0, start - hitsPerPage);
            break;
          } else if (line.charAt(0) == 'n') {
            if (start + hitsPerPage < numTotalHits) {
              start+=hitsPerPage;
            }
            break;
          } else {
            int page = Integer.parseInt(line);
            if ((page - 1) * hitsPerPage < numTotalHits) {
              start = (page - 1) * hitsPerPage;
              break;
            } else {
              System.out.println("No such page");
            }
          }
        }
        if (quit) break;
        end = Math.min(numTotalHits, start + hitsPerPage);
      }
    }
    
    return NumbrDoc_Scor;
  }
  
}



























































