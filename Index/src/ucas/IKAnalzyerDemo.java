/**
 * IK 中文分词  版本 5.0.1
 * IK Analyzer release 5.0.1
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * 源代码由林良益(linliangyi2005@gmail.com)提供
 * 版权声明 2012，乌龙茶工作室
 * provided by Linliangyi and copyright 2012 by Oolong studio
 * 
 * 
 */
package ucas;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 使用IKAnalyzer进行分词的演示
 * 2012-10-22
 *
 */
public class IKAnalzyerDemo {
	
	public static void main(String[] args){
		System.out.println(Spilt2Words("　　新浪体育讯　北京时间12月5日《Dime》报道，洛杉矶湖人将于周六上午8点30分作客波士顿TD花园球馆，挑战凯尔特人，所以在昨天负于奇才的比赛之后，他们就已立即动身赶往波士顿。　　当地时间今天早间，有人发现湖人当家球星科比-布莱恩特出现在了当地一家餐馆，而和他共进早餐的并非他的队友，而是即将在赛场碰面的对手拉简-隆多。于是，一时间谣言四起，人们纷纷猜测两位全明星球员的这次私下会面，究竟意味着什么。　　有关隆多可能加盟湖人的流言也得到了《雅虎体育》爆料天神阿德里安-沃纳洛夫斯基的部分证实。他在今天早些时候接受采访时谈到了湖人队的控卫问题，认为林书豪仅仅是紫金军团在一号位的临时选择，并非是一名合格的首发控卫，而湖人需要在史蒂夫-纳什倒下之后找到一个新的组织者。　　沃纳洛夫斯基的原话是：“我认为林只是湖人的权宜之计，他是一名NBA替补级别的控卫。很明显是因为他们的阵容缺陷，以及史蒂夫-纳什因伤赛季报销，他才被强行顶上首发。你能看到林自信全无，在他和科比之间，显然没有产生很好的化学反应。”　　相比之下，隆多和科比虽然曾经作为总决赛的对手相互敌对，但彼此间却非常尊重。隆多目前的合同已进入最后一年，如果丹尼-安吉不打算在明年夏天与之续约，那么凯尔特人会在2月交易截止期前尽力将他送走。　　如果隆多在波士顿呆到明年6月，那么按照劳资协议规定，绿军可以提供比其他球队更高的合同，但问题的关键在于，志在重建的他们，会愿意为隆多送上顶薪合同么？而且对于隆多来说，是否愿意留下来陪着年轻人不断成长，亦或是去一支更有竞争力的球队追求胜利，也存在着巨大疑问。　　当然，仅仅一起吃了顿早餐，就说隆多要去湖人了，这未免为时过早。只是考虑到两支球队以及两名球员各自所处的境况，这实在让人忍不住想入非非。　　(熊猫) , "));
	}

	public  static String Spilt2Words(String content) {
		String resString = "";
		//构建IK分词器，使用smart分词模式
		Analyzer analyzer = new IKAnalyzer(true);
		
		//获取Lucene的TokenStream对象
	    TokenStream ts = null;
		try {
			//myfield什么意思
			ts = analyzer.tokenStream("myfield", new StringReader(content));
		    //获取词元文本属性
		    CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
		    	    
		    //重置TokenStream（重置StringReader）
			ts.reset(); 
			//迭代获取分词结果
			while (ts.incrementToken()) {
				resString+=term.toString()+"|";
			}
			//关闭TokenStream（关闭StringReader）
			ts.end();   // Perform end-of-stream operations, e.g. set the final offset.

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//释放TokenStream的所有资源
			if(ts != null){
		      try {
				ts.close();
		      } catch (IOException e) {
				e.printStackTrace();
		      }
			}
	    }
		return resString;
	}
}
