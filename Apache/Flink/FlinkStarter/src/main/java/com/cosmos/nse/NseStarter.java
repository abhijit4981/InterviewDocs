package com.cosmos.nse;

import java.util.List;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class NseStarter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(NseStarter.class, args);

        ParameterTool params = ParameterTool.fromArgs(args);
        
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSet<Integer> amounts = (DataSet<Integer>) env.fromElements(1, 29, 40, 50);
        
        int threshold = 30;
        try {
			List<Integer> collect = amounts
			  .filter(a -> a > threshold)
			  .reduce((integer, t1) -> integer + t1)
			  .collect();
			System.out.println(collect);
			env.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
