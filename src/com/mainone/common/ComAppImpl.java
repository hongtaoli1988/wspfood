package com.mainone.common;

import java.util.ArrayList;
import java.util.List;

import com.mainone.dao.IBaseServiceDao;
import com.mainone.util.DBRecord;

public class ComAppImpl implements ComApp {
    private IBaseServiceDao iBaseServiceDaoB;
	
	public String getCompanyNameInfoList(String comId) {
		// TODO Auto-generated method stub
		DBRecord db=null; 
		String str="";
		List list=null;
		String sql="select COM_ID,COM_NAME  from MO_COMPANY_INFO where  RECORD_STATE='0'";
		str+="<option selected value=''>����</option>";
		    try {
				list=(List)this.iBaseServiceDaoB.getSqlJdbc(sql);
			    for(int i=0;i<list.size();i++){
			        db = (DBRecord)list.get(i);
			        
			        if(db.getString("COM_ID").equals(comId)){
			        
		        	str+="<option value="+db.getString("COM_ID").trim()+" selected >"+db.getString("COM_NAME").trim()+"</option>";
			        }
			        else{
			        	str+="<option value="+db.getString("COM_ID").trim()+" >"+db.getString("COM_NAME").trim()+"</option>";
			        }
			}
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return str;  
	}
	public String getComList(String com_ID) {
		// TODO Auto-generated method stub
		DBRecord db=null; 
		String str="";
		List list=null;
		String sql1="select COM_ID,COM_NAME  from MO_COMPANY_INFO where  RECORD_STATE='0' and COM_ID ="+com_ID;
			try {
				list=(List)this.iBaseServiceDaoB.getSqlJdbc(sql1);
				db=(DBRecord) list.get(0);
				str+="<option value="+db.getString("COM_ID").trim()+" selected >"+db.getString("COM_NAME").trim()+"</option>";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		return str;
	}
	
	//ȥ������
	public String getCompanyNameInfoList1(String comId) {
		// TODO Auto-generated method stub
		DBRecord db=null; 
		String str="";
		List list=null;
		String sql="select COM_ID,COM_NAME  from MO_COMPANY_INFO where  RECORD_STATE='0'";
		    try {
				list=(List)this.iBaseServiceDaoB.getSqlJdbc(sql);
			    for(int i=0;i<list.size();i++){
			        db = (DBRecord)list.get(i);
			        
			        if(db.getString("COM_ID").equals(comId)){
			        
		        	str+="<option value="+db.getString("COM_ID").trim()+" selected >"+db.getString("COM_NAME").trim()+"</option>";
			        }
			        else{
			        	str+="<option value="+db.getString("COM_ID").trim()+" >"+db.getString("COM_NAME").trim()+"</option>";
			        }
			}
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return str;  
	}

	public String getSubComId(String comId) {
		// TODO Auto-generated method stub
		String sql="select SUB_COM_ID from MO_COMPANY_INFO where RECORD_STATE='0' and COM_ID="+comId;
		String subComId="";
		DBRecord dbr=null;
		try {
			List list=this.iBaseServiceDaoB.getSqlJdbc(sql);
			if(list!=null&&list.size()>0){
			   dbr=(DBRecord)list.get(0);
				subComId=dbr.getString("SUB_COM_ID");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return subComId="";
		}
		return subComId;
	}
	/**
	 * �����û�ID���ù�˾�б�
	 * @param userId
	 * @param comId
	 * @return
	 */
	public String getCompanyNameInfoListNew(String userId,String comId) {
		// TODO Auto-generated method stub
		DBRecord db=null; 
		String str="";
		List list=null;
		/**�жϴ����ڿ繫˾��������Ƿ���� list.size()>0 :����;list.size()<0 ������*/
		String sql1 = "select * from USER_SPAN_COM_INFO where RECORD_STATE = '0' and System_FLAG ='3' and USER_ID='"+userId+"'";
		List listSpan = new ArrayList();
		try{
			listSpan = iBaseServiceDaoB.getSqlJdbc(sql1);
			//System.out.println("listSpan.size()==="+listSpan.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		if(listSpan.size()>0){
			
				for(int i=0;i<listSpan.size();i++){
					DBRecord dbr = (DBRecord)listSpan.get(i);
					if(dbr.getField("COM_ID").equals(comId)){
						str+="<option selected value="+comId+" selected>"+dbr.getField("COM_NAME")+"</option>";
					}else{
						str+="<option value="+dbr.getField("COM_ID")+">"+dbr.getString("COM_NAME").trim()+"</option>";
					}
				}
			
			
		}else{
		String sql="select COM_ID,COM_NAME  from MO_COMPANY_INFO where  RECORD_STATE='0'";
		str+="<option selected value=''>����</option>";
		    try {
				list=(List)this.iBaseServiceDaoB.getSqlJdbc(sql);
			    for(int i=0;i<list.size();i++){
			        db = (DBRecord)list.get(i);
			        
			        if(db.getString("COM_ID").equals(comId)){
			        
		        	str+="<option value="+db.getString("COM_ID").trim()+" selected >"+db.getString("COM_NAME").trim()+"</option>";
			        }
			        else{
			        	str+="<option value="+db.getString("COM_ID").trim()+" >"+db.getString("COM_NAME").trim()+"</option>";
			        }
			}
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		    return str;  
	}
	
	/**
	 * �����û�ID���ù�˾�б�,ȥ������
	 * @param userId
	 * @param comId
	 * @return
	 */
	public String getCompanyNameInfoListNew1(String userId,String comId) {
		// TODO Auto-generated method stub
		DBRecord db=null; 
		String str="";
		List list=null;
		/**�жϴ����ڿ繫˾��������Ƿ���� list.size()>0 :����;list.size()<0 ������*/
		String sql1 = "select * from USER_SPAN_COM_INFO where RECORD_STATE = '0' and System_FLAG ='3' and USER_ID='"+userId+"'";
		List listSpan = new ArrayList();
		try{
			listSpan = iBaseServiceDaoB.getSqlJdbc(sql1);
			//System.out.println("listSpan.size()==="+listSpan.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		if(listSpan.size()>0){
			
				for(int i=0;i<listSpan.size();i++){
					DBRecord dbr = (DBRecord)listSpan.get(i);
					if(dbr.getField("COM_ID").equals(comId)){
						str+="<option selected value="+comId+" selected>"+dbr.getField("COM_NAME")+"</option>";
					}else{
						str+="<option value="+dbr.getField("COM_ID")+">"+dbr.getString("COM_NAME").trim()+"</option>";
					}
				}
			
			
		}else{
		String sql="select COM_ID,COM_NAME  from MO_COMPANY_INFO where  RECORD_STATE='0'";
		    try {
				list=(List)this.iBaseServiceDaoB.getSqlJdbc(sql);
			    for(int i=0;i<list.size();i++){
			        db = (DBRecord)list.get(i);
			        
			        if(db.getString("COM_ID").equals(comId)){
			        
		        	str+="<option value="+db.getString("COM_ID").trim()+" selected >"+db.getString("COM_NAME").trim()+"</option>";
			        }
			        else{
			        	str+="<option value="+db.getString("COM_ID").trim()+" >"+db.getString("COM_NAME").trim()+"</option>";
			        }
			}
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		    return str;  
	}
	
	
	/**
	 * �����û�ID���ù�˾�б�
	 * @param userId
	 * @param comId
	 * @return
	*/
	public String getComListNew(String userId,String comId) {
		// TODO Auto-generated method stub
		DBRecord db=null; 
		String str="";
		List list=null;
		/**�жϴ����ڿ繫˾��������Ƿ���� list.size()>0 :����;list.size()<0 ������*/
		String sql1 = "select * from USER_SPAN_COM_INFO where RECORD_STATE = '0' and System_FLAG ='3' and USER_ID='"+userId+"'";
		List listSpan = new ArrayList();
		try{
			listSpan = iBaseServiceDaoB.getSqlJdbc(sql1);
			//System.out.println("listSpan.size()==="+listSpan.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		if(listSpan.size()>0){
			
				for(int i=0;i<listSpan.size();i++){
					DBRecord dbr = (DBRecord)listSpan.get(i);
					if(dbr.getField("COM_ID").equals(comId)){
						str+="<option selected value="+comId+" selected>"+dbr.getField("COM_NAME")+"</option>";
					}else{
						str+="<option value="+dbr.getField("COM_ID")+">"+dbr.getString("COM_NAME").trim()+"</option>";
					}
				}
			
			
		}else{
		String sql2="select COM_ID,COM_NAME  from MO_COMPANY_INFO where  RECORD_STATE='0' and COM_ID ="+comId;
			try {
				list=(List)this.iBaseServiceDaoB.getSqlJdbc(sql2);
				db=(DBRecord) list.get(0);
				str+="<option value="+db.getString("COM_ID").trim()+" selected >"+db.getString("COM_NAME").trim()+"</option>";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return str;
	}
	
	/**
	 * ����comId��ȡ��˾����
	 */
	public String getComName(String comId){
		
		String comName = "";
		String sql = "select COM_NAME from MO_COMPANY_INFO where RECORD_STATE='0' and COM_ID ="+comId;
		try {
			comName = this.iBaseServiceDaoB.selectOne(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comName;
	}
	/**
	 * �����û�ID���ù�˾�б�
	 * @param userId
	 * @param comId
	 * @return
	 */
	public String getCompanyNameInfoListUseInServer(String userId,String comId) {
		// TODO Auto-generated method stub
		DBRecord db=null; 
		String str="";
		List list=null;
		/**�жϴ����ڿ繫˾��������Ƿ���� list.size()>0 :����;list.size()<0 ������*/
		String sql1 = "select * from USER_SPAN_COM_INFO where RECORD_STATE = '0' and System_FLAG ='3' and USER_ID='"+userId+"'";
		List listSpan = new ArrayList();
		try{
			listSpan = iBaseServiceDaoB.getSqlJdbc(sql1);
			//System.out.println("listSpan.size()==="+listSpan.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		if(listSpan.size()>0){
			
				for(int i=0;i<listSpan.size();i++){
					DBRecord dbr = (DBRecord)listSpan.get(i);
					if(dbr.getField("COM_ID").equals(comId)){
						str+="<option selected value="+comId+" selected>"+dbr.getField("COM_NAME")+"</option>";
					}else{
						str+="<option value="+dbr.getField("COM_ID")+">"+dbr.getString("COM_NAME").trim()+"</option>";
					}
				}
			
			
		}else{
		String sql="select COM_ID,COM_NAME  from MO_COMPANY_INFO where  RECORD_STATE='0'";
		str+="<option selected value=''>��ѡ��ֹ�˾</option>";
		    try {
				list=(List)this.iBaseServiceDaoB.getSqlJdbc(sql);
			    for(int i=0;i<list.size();i++){
			        db = (DBRecord)list.get(i);
			        
			        if(db.getString("COM_ID").equals(comId)){
			        
		        	str+="<option value="+db.getString("COM_ID").trim()+" selected >"+db.getString("COM_NAME").trim()+"</option>";
			        }
			        else{
			        	str+="<option value="+db.getString("COM_ID").trim()+" >"+db.getString("COM_NAME").trim()+"</option>";
			        }
			}
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		    return str;  
	}
	
	
	public String getPositionList(String posotionId) {
		// TODO Auto-generated method stub
		DBRecord db=null; 
		String str="";
		List list=null;
		String sql="select Positionid,Name from Positioninfo where Delflag='0' order by Name ASC ";
		str+="<option selected value=''>����</option>";
		    try {
				list=(List)this.iBaseServiceDaoB.getSqlJdbc(sql);
			    for(int i=0;i<list.size();i++){
			        db = (DBRecord)list.get(i);
			        String id = db.getString("Positionid")==null?"":db.getString("Positionid");
			        String Name = db.getString("Name")==null?"":db.getString("Name");
			        if(id.equals(posotionId)){
			        	str+="<option value="+id+" selected>"+Name+"</option>";
			        }
			        else{
			        	str+="<option value="+id+">"+Name+"</option>";
			        }
			    }
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return str;  
	}
	
	/**
	 * ����Positionid��ȡ��λ����
	 */
	public String getPositionName(String Positionid){
		
		String positionName = "";
		String sql = "select Name from Positioninfo where Delflag='0' and Positionid ="+Positionid;
		try {
			positionName = this.iBaseServiceDaoB.selectOne(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return positionName;
	}
	
	
	
	public void setIBaseServiceDaoB(IBaseServiceDao baseServiceDaoB) {
		iBaseServiceDaoB = baseServiceDaoB;
	}
}
