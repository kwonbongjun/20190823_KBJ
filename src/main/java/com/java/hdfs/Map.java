package com.java.hdfs;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//Mapper<(입력키<행번호> : 입력값<행의글자>) , (출력키<글자> : 출력값<1>)>
public class Map extends Mapper<LongWritable, Text, Text, IntWritable> {

	// 출력 키 변수
	protected Text textKey = new Text();
	// 출력 값 변수
	//protected IntWritable intValue = new IntWritable(1);
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		// 출력 키에 넣을 문자열 변수
		String[] values=value.toString().split(",");
		String strKey = values[0];

		textKey.set(values[0]+","+values[1]);
		//4.출력의 값으로 정수형 1을  intwritable 자료형으로 변환
		
		if(!"NA".equals(values[15])) {
			int time=Integer.parseInt(values[15]);
			if(time>0) {
			IntWritable intValue = new IntWritable(time);
			context.write(textKey, intValue);
			}
		}
		// 전체 결과 출력하기

	}
	
}
