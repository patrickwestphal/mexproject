package log4mex;

import org.aksw.mex.log4mex.MEXSerializer;
import org.aksw.mex.log4mex.MyMEXVO;
import org.aksw.mex.util.MEXConstant;
import org.aksw.mex.util.MEXEnum.*;

/**
 * Created by esteves on 01.07.15.
 * The simplest example of MEX
 */
public class Example01 {

    public static void main(String[] args) {


        System.out.println("starting example 01...");

        MyMEXVO mex = new MyMEXVO();

        try{
            /* (1) basic authoring provenance */
            mex.setAuthorName("D Esteves");
            String confID = mex.addConfiguration();
            /* (2) the dataset */
            mex.Configuration(confID).DataSet().setName("mydataset.csv");
            /* (3) the features */
            String[] features = {"min", "max", "ope", "clo"};
            mex.Configuration(confID).addFeature(features);
            /* (4) the algorithms and hyperparameters */
            String alg01ID = mex.Configuration(confID).addAlgorithm(EnumAlgorithms.NaiveBayes).getIdentifier();
            /* (5) the executions */


            String execID = mex.Configuration(confID).addExecution(EnumExecutionsType.OVERALL, EnumPhases.TEST);
            {
                //your models call here !
            }
            /* (6) the performances for the executions */
            mex.Configuration(confID).Execution(execID).setAlgorithm(alg01ID);
            mex.Configuration(confID).Execution(execID).addPerformance(EnumMeasures.ACCURACY, .96);
            mex.Configuration(confID).Execution(execID).addPerformance(EnumMeasures.ERROR, .04);
             /* (7) saving the mex file */
            MEXSerializer.getInstance().saveToDisk("/Users/dnes/Github/mexproject/metafiles/log4mex/ex001",
                    "http://mex.aksw.org/examples/", mex, MEXConstant.EnumRDFFormats.TTL);

            System.out.println("The MEX file [ex001] has been successfully created: share it ;-)");

            System.exit(0);

        }catch (Exception e){
            System.out.println(e.toString());
        }



    }
}