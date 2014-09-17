/**
 * Sample Recommender user-based recommender
 */
package au.com.ml.UserRec;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;


/**
 * @author u321146
 *
 */
public class UserItemRecommender {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws TasteException 
	 */
	public static void main(String[] args) throws IOException, TasteException {
		DataModel model = createModel();
		//for large data sets item based recommender is appropriate
        // create a simple recommender on our data
       
        
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		
		CachingRecommender cachingRecommender = new CachingRecommender(recommender);
		 
		//Recommender cachingRecommender = new CachingRecommender(recommender);
		//Userid and Number of recommendations
		List<RecommendedItem> recommendations = recommender.recommend(2, 3);
		//List<RecommendedItem> recommendations =cachingRecommender.recommend(2, 3);
		for (RecommendedItem recommendation :recommendations)
		{
			System.out.println(recommendation);

		}
		
		
	}

	private static DataModel createModel() throws IOException {
		//Load Data and create model
		//DataModel model = new FileDataModel(new File("data/FF2014_09_12.csv"));
		DataModel model = new FileDataModel(new File("data/dataset.csv"));
		return model;
	}

}
