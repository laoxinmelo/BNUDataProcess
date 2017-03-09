//package master.operation.integrate.extend;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.regex.*;
//
//import master.dataobject.DataFile;
//
//public class artificial_IntegrateData {
//	public static void main(String[] args) throws Exception {
//		//ï¿½ï¿½ï¿½Ã³ï¿½Ê¼ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½È¡ï¿½Ä¼ï¿½ï¿½ï¿½ï¿½Ðµï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ä¼ï¿½
//		artificial_IntegrateData id = new artificial_IntegrateData();
//		File root = new File("E:\\Program Files\\Java\\BNU_Data\\DataIntegrate\\Artificial");
//
//		//ï¿½ï¿½ï¿½Ä¼ï¿½ï¿½ï¿½ï¿½Ð·ï¿½ï¿½ï¿½
//		ArrayList<String> strlist = new ArrayList<String>(); //ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ä¼ï¿½ï¿½Ä±ï¿½Ç©
//		ArrayList<DataFile> dflist = new ArrayList<DataFile>();
//
//		id.ReadFiles(root,strlist,dflist);
//
//
//		for(int i=0;i<dflist.size();i++)
//		{
//			id.SaveResult(id.Integrate((ArrayList)dflist.get(i).getfList()), dflist.get(i).getMark());
//			System.out.println((i+1)+"__finished");
//			Thread.sleep(5*1000);
//		}
//	}
//
//	/**
//	 * 读取文件
//	 * @param root
//	 * @param strlist
//	 * @param dflist
//	 * @throws Exception
//     */
//	void ReadFiles(File root,ArrayList<String> strlist,ArrayList<DataFile> dflist) throws Exception
//	{
//		File[] fs = root.listFiles();
//
//		for(int i=0;i<fs.length;i++)
//		{
//
//			if(fs[i].getAbsolutePath().contains(".mnd") || fs[i].getAbsolutePath().contains(".dgn"))
//			{
//				getFileGroups(strlist,fs[i],dflist);
//			}
//
//			if(fs[i].isDirectory()){
//				try{
//					ReadFiles(fs[i],strlist,dflist);
//				}
//				catch(Exception e){}
//			}
//		}
//
//
//	}
//
//	/**
//	 * @Purpose:ï¿½ï¿½ï¿½ï¿½Ã¿ï¿½ï¿½ï¿½Ä¼ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ð·ï¿½ï¿½ï¿½
//	 * @param strlist
//	 * @param f
//	 * @param dflist
//	 * @throws Exception
//	 */
//	void getFileGroups(ArrayList<String> strlist,File f,ArrayList<DataFile> dflist) throws Exception
//	{
//		//ï¿½ï¿½ï¿½Ä¼ï¿½ï¿½ï¿½ï¿½Úµï¿½ï¿½Ä¼ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ê¾ï¿½ï¿½Ç©
//		String source = f.getParent();
//		String[] array = source.split("\\\\");
//		String mark = "";
//
//		if(array.length == 9)
//		{
//			mark = array[array.length-2]+"_"+array[array.length-1];
//		}
//		else if(array.length == 10)
//		{
//			mark = array[array.length-3]+"_"+array[array.length-2]+"_"+array[array.length-1];
//		}
//
//
//		DataFile df = new DataFile();//ï¿½ï¿½ÎªÒ»ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½,ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½dflistï¿½Ä³ï¿½ï¿½ï¿½,Ê¹ï¿½ï¿½ï¿½ï¿½strlistï¿½Ä³ï¿½ï¿½È±ï¿½ï¿½ï¿½Ò»ï¿½ï¿½
//
//		//ï¿½ï¿½ï¿½ï¿½ï¿½Ä¼ï¿½ï¿½ï¿½ï¿½Ð¶ï¿½ï¿½ï¿½Ó¦ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ò»ï¿½ï¿½ï¿½
//		if(!strlist.contains(mark))
//		{
//			strlist.add(mark);
//
//			dflist.add(df);
//			dflist.get(strlist.size()-1).setMark(mark);
//			dflist.get(strlist.size()-1).getfList().add(f);
//			System.out.println(f.getAbsolutePath());
//		}
//		else
//		{
//			for(int i=0;i<dflist.size();i++)
//			{
//				if(dflist.get(i).getMark().equals(mark))
//				{
//					dflist.get(i).getfList().add(f);
//				}
//			}
//
//		}
//	}
//
//	/**
//	 * @Purpose:ï¿½ï¿½Í¬Ò»ï¿½ï¿½ï¿½ï¿½ï¿½Ä¼ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
//	 * @param flist
//	 * @param mark
//	 * @return
//	 * @throws Exception
//	 */
//	ArrayList<String> Integrate(ArrayList<File> flist) throws Exception
//	{
//		//ï¿½ï¿½ï¿½Ã³ï¿½Ê¼ï¿½ï¿½ï¿½ï¿½
//		ArrayList<String> strList = new ArrayList<String>();
//
//		//ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½,ï¿½ï¿½ï¿½ï¿½Ê±ï¿½ï¿½Äºï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ý²ï¿½ï¿½Ü¶ï¿½È¡
//		String reg = "\\t";
//		Pattern patt = Pattern.compile(reg);
//
//		/*
//		 * ï¿½ï¿½È¡ï¿½ï¿½Ò»ï¿½ï¿½ï¿½Ä¼ï¿½ï¿½ï¿½ï¿½ï¿½Í¬ï¿½ï¿½ï¿½ï¿½ï¿½Ò»ï¿½ï¿½ï¿½È¡ï¿½ï¿½ï¿½ï¿½
//		 * */
//		InputStreamReader isr = new InputStreamReader(new FileInputStream(flist.get(0)),"utf-8");
//		BufferedReader br = new BufferedReader(isr);
//		String temp = br.readLine();
//		String title = temp;
//		temp = br.readLine();
//
//		while(temp!=null && (!patt.matcher(temp).find()))
//		{
//			title += "\r\n" + temp ;
//			temp  = br.readLine();
//		}
//
//		System.out.println(title);
//		//ï¿½ï¿½ï¿½ï¿½Í¬Ò»ï¿½Ð±ï¿½Ä²ï¿½Í¬ï¿½Ä¼ï¿½
//		for(int i=0;i<flist.size();i++)
//		{
//
//			//ï¿½ï¿½È¡ï¿½Ä¼ï¿½ï¿½Ä±ï¿½ï¿½ï¿½
//			isr = new InputStreamReader(new FileInputStream(flist.get(i)));
//			br = new BufferedReader(isr);
//			temp = br.readLine();
//
//
//			//ï¿½ï¿½ï¿½ï¿½Öµï¿½ï¿½Óµï¿½ï¿½ï¿½Ì¬ï¿½ï¿½ï¿½éµ±ï¿½ï¿½
//			while(temp!=null)
//			{
//
//				if(patt.matcher(temp).find())
//				{
//					//ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
//					String[] array_temp = temp.split("	");
//					String data_pre = "";
//
//					for(int j=0;j<array_temp.length;j++)
//					{
//						//ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
//						if(j==0)
//						{
//							if(array_temp[j].contains(" "))
//							{
//								//ï¿½Ô¸ï¿½Ê½ï¿½ï¿½ï¿½Ðµï¿½ï¿½ï¿½
//								String tempo = array_temp[j].substring(0, array_temp[j].lastIndexOf(":"));
//								tempo = tempo.replaceAll("-", "/");
//								array_temp[j] = tempo;
//
//
//							}else
//							{
//								try{
//									//ï¿½Ô¸ï¿½Ê½ï¿½ï¿½ï¿½ï¿½Î¢ï¿½ï¿½
//									String tempo = array_temp[j].substring(array_temp[j].indexOf("/")+1, array_temp[j].lastIndexOf("+"));
//									tempo = tempo.replaceAll("-", "/");
//									tempo = tempo.replaceAll("[a-zA-Z]", " ");
//									tempo = tempo.substring(0, tempo.lastIndexOf(":"));
//									array_temp[j] = tempo;
//
//								}catch(Exception e)
//								{
//
//								}
//
//							}
//						}
//
//						data_pre += array_temp[j] + "	";
//					}
//
//					//É¸Ñ¡ï¿½ï¿½ï¿½Ï¸ï¿½ï¿½ï¿½ï¿½ï¿½Ý¸ï¿½Ê½
//					if(data_pre.startsWith("20"))
//					{
//						System.out.println(data_pre);
//						strList.add(data_pre);
//					}
//				}
//				temp = br.readLine();
//			}
//		}
//		//ï¿½Ô¶ï¿½È¡ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
//		strList.sort(null);
//		strList.add(0, title);
//
//		br.close();
//
//		return strList;
//
//	}
//
//
//	/**
//	 * @Purpose:ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
//	 * @param strlist
//	 * @throws Exception
//	 */
//	void SaveResult(ArrayList<String> strlist,String mark) throws Exception
//	{
//		//ï¿½ï¿½ï¿½ï¿½ï¿½Ä¼ï¿½ï¿½ï¿½
//		String address = "Result/Artificial/" + mark;
//		File f = new File(address);
//		f.mkdir();
//
//		//ï¿½ï¿½ï¿½ï¿½Ð´ï¿½ï¿½txtï¿½Ä¼ï¿½ï¿½ï¿½
//		BufferedWriter bw = new BufferedWriter(new FileWriter(address+"/data_"+mark+".txt",true));
//
//		int count = strlist.size();
//		for(int i=0;i<count;i++)
//		{
//			bw.write(strlist.get(i) + "\r\n");
//		}
//
//		bw.flush();  bw.close();
//	}
//}
