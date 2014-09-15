/**
 * Sample Recommender user-based recommender
 */
package au.com.ml.energydata;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;

/**
 * @author u321146
 *
 */
public class FridgeRecommender {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws TasteException 
	 */
	public static void main(String[] args) throws IOException, TasteException {
		//dataset is of the format userID,itemID,value
		DataModel model = new FileDataModel(new File("data/dataset.csv"));
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		
		//Recommender cachingRecommender = new CachingRecommender(recommender);
		//Userid and Number of recommendations
		List<RecommendedItem> recommendations = recommender.recommend(2, 3);
		//List<RecommendedItem> recommendations =cachingRecommender.recommend(2, 3);
		for (RecommendedItem recommendation :recommendations)
		{
			System.out.println(recommendation);

		}
		
		
	}

}
