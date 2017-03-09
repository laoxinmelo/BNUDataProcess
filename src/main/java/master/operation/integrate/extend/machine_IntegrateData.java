//package master.operation.integrate.extend;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.regex.*;
//
//import master.dataobject.DataFile;
//
//public class machine_IntegrateData {
//	public static void main(String[] args) throws Exception {
//		//���ó�ʼ��������ȡ�ļ����е������ļ�
//		machine_IntegrateData id = new machine_IntegrateData();
//		File root = new File("E:\\Program Files\\Java\\BNU_Data\\DataIntegrate\\Machine");
//
//		//���ļ����з���
//		ArrayList<String> strlist = new ArrayList<String>(); //������������ļ��ı�ǩ
//		ArrayList<DataFile> dflist = new ArrayList<DataFile>();
//		id.ReadFiles(root,strlist,dflist);
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
//	 * @Purpose:�����ļ���,��ȡ�����ļ���
//	 * @param root
//	 * @return
//	 * @throws Exception
//	 */
//	void ReadFiles(File root,ArrayList<String> strlist,ArrayList<DataFile> dflist) throws Exception
//	{
//		//���ó�ʼ����
//		File[] fs = root.listFiles();
//
//		for(int i=0;i<fs.length;i++)
//		{
//
//			if(fs[i].getAbsolutePath().contains(".csv"))
//			{
//				System.out.println(fs[i].getParentFile());
//				//��ÿһ���������ļ����з���
//				getFileGroups(strlist,fs[i],dflist);
//			}
//
//			//ʵ�ֱ����ĵ���
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
//	 * @Purpose:����ÿ��csv�ļ�������������з���
//	 * @param strlist
//	 * @param f
//	 * @param dflist
//	 * @throws Exception
//	 */
//	void getFileGroups(ArrayList<String> strlist,File f,ArrayList<DataFile> dflist) throws Exception
//	{
//		//�ҳ��ļ��ı�ǩ
//		String mark = f.getName().substring(0, f.getName().lastIndexOf("_"));
//		DataFile df = new DataFile();//��Ϊһ�������,��������dflist�ĳ���,ʹ����strlist�ĳ��ȱ���һ��
//
//		//�����ļ����ж���Ӧ��������һ���
//		if(!strlist.contains(mark))
//		{
//			strlist.add(mark);
//
//			dflist.add(df);
//			dflist.get(strlist.size()-1).mark = mark;
//			dflist.get(strlist.size()-1).fList.add(f);
//		}
//		else
//		{
//			for(int i=0;i<dflist.size();i++)
//			{
//				if(dflist.get(i).mark.equals(mark))
//				{
//					dflist.get(i).fList.add(f);
//				}
//			}
//
//		}
//	}
//
//	/**
//	 * @Purpose:��ͬһ�����ļ���������
//	 * @param flist
//	 * @param mark
//	 * @return
//	 * @throws Exception
//	 */
//	ArrayList<String> Integrate(ArrayList<File> flist) throws Exception
//	{
//		//���ó�ʼ����
//		ArrayList<String> strList = new ArrayList<String>();
//
//		//�����������,����ʱ��Ĳ��ܶ�ȡ
//		String regex = ":";
//		Pattern pat = Pattern.compile(regex);
//		String reg = "\"";
//		Pattern patt = Pattern.compile(reg);
//
//		/*
//		 * ��ȡ��һ���ļ�����ͬ�����һ���ȡ����
//		 * */
//		InputStreamReader isr = new InputStreamReader(new FileInputStream(flist.get(0)));
//		BufferedReader br = new BufferedReader(isr);
//		String temp = br.readLine();
//		String title = temp.replaceAll("Date,Time", "Date_Time");
//		temp = br.readLine();
//
//		while(temp!=null && (patt.matcher(temp).find() || !pat.matcher(temp).find() ))
//		{
//			title += "\r\n" + temp ;
//			temp  = br.readLine();
//		}
//
//		System.out.println(title);
//		//����ͬһ�б�Ĳ�ͬ�ļ�
//		for(int i=0;i<flist.size();i++)
//		{
//
//			//��ȡ�ļ��ı���
//			isr = new InputStreamReader(new FileInputStream(flist.get(i)));
//			br = new BufferedReader(isr);
//			temp = br.readLine();
//
//
//			//����ֵ��ӵ���̬���鵱��
//			while(temp!=null)
//			{
//				if(!(patt.matcher(temp).find() || !pat.matcher(temp).find()))
//				{
//					//��������
//					String[] array_temp = temp.split(",");
//					String data_pre = "";
//					for(int j=0;j<array_temp.length;j++)
//					{
//						if(j==1)
//						{
//							String temp_x = array_temp[j-1].replaceAll("[^0-9]", "/") + " " + array_temp[j];
//							data_pre +=  temp_x.substring(0, temp_x.lastIndexOf(":"));
//							continue;
//						}
//						else if(j > 1)
//						{
//							data_pre += "," + array_temp[j];
//							continue;
//						}
//
//					}
//						strList.add(data_pre);
//				}
//
//				temp = br.readLine();
//			}
//
//		}
//
//		//�Զ�ȡ�����������
//		strList.sort(null);
//		strList.add(0, title);
//
//		br.close();
//
//		return strList;
//	}
//
//	/**
//	 * @Purpose:������
//	 * @param strlist
//	 * @throws Exception
//	 */
//	void SaveResult(ArrayList<String> strlist,String mark) throws Exception
//	{
//		//�����ļ���
//		String address = "Result/Machine/" + mark;
//		File f = new File(address);
//		f.mkdir();
//
//		//����д��txt�ļ���
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
