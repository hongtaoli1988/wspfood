package com.mainone.app.customer;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mainone.dao.IBaseServiceDao;
import com.mainone.model.sfa.SfaCustomerInfo;
import com.mainone.model.sfa.SfaLinkManInfo;
import com.mainone.model.sfa.SfaUseFlowInfo;
import com.mainone.util.DBRecord;

/**
 * @author��zzf
 * ����ʱ�䣺Jul 9, 2013 10:29:29 AM
 * ��˵�����ͻ�����
 */
public class CustomerProtectAppImpl implements CustomerProtectApp {
	
	private IBaseServiceDao iBaseServiceDaoA;
	public void setIBaseServiceDaoA(IBaseServiceDao baseServiceDaoA) {
		iBaseServiceDaoA = baseServiceDaoA;
	}
	
	
	/**
	 * ��ȡ�����ͻ���Ϣ
	 * @param customerName
	 * @return
	 * @throws Exception
	 */
	public DBRecord getSingleCustomerInfo(String customerName, String comId) throws Exception {
		
		DBRecord dbr = null;
		String sql = " select * from SFA_Customer_info where CompanyName = '" +customerName+ "' and CompanyID = '" +comId+ "'";
		try {
			dbr = iBaseServiceDaoA.selectRow(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dbr;
	}
	
	
	/**
	 * ��֤�ͻ��绰
	 * @param customerTel
	 * @param cutyId
	 * @return
	 * @throws Exception
	 */
	public DBRecord checkCustomerTel(String customerTel, String comId) throws Exception {
		
		DBRecord dbr = null;
		String sql = " select CustomerTel from SFA_Customer_info where CustomerTel = '" +customerTel+ "' and CompanyID = '" +comId+ "'";
		try {
			dbr = iBaseServiceDaoA.selectRow(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dbr;
	}
	
	
	/**
	 * ����ҵı����ͻ�
	 * @param sci
	 * @return
	 * @throws Exception
	 */
	public int save(SfaCustomerInfo sci, SfaLinkManInfo sli) throws Exception{
		
		int id = 0;
		try {
			//��ӿͻ�
			iBaseServiceDaoA.saveObject(sci);
			id = sci.getCustomerId();
			//���������ˮ
			SfaUseFlowInfo sfi = new SfaUseFlowInfo();
			sfi.setCustomerId(id);
			sfi.setEmployeeId(sci.getEmployeeId());
			sfi.setDepartId(sci.getDepartId());
			sfi.setCompanyId(sci.getCompanyId());
			sfi.setAreaId(sci.getAreaId());
			sfi.setStartTime(new Date());
			sfi.setStatus("0");
			sfi.setRemark(null);
			sfi.setOperateTime(new Date());
			sfi.setCustomerComeId(sci.getCustomerComeId());
			sfi.setIfAddSelf("1");
			sfi.setResourceState("0");
			iBaseServiceDaoA.saveObject(sfi);
			//�����ϵ��
			sli.setCustomerId(sci.getCustomerId());
			iBaseServiceDaoA.saveObject(sli);
			} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return id;
	}
	// ��֤�û���ʽ
    public boolean yanZhengCustomer(String customername) throws Exception {
        boolean result = false;
        try {
            boolean beginYanZheng = false;
            boolean endYanZheng = false;
            boolean middleYanZheng = true;
            // if(customername.indexOf(" ")!=-1){
            // return result;
            // }
            String source = customername.trim();
            // ���ַ�
            String beginstr = source.substring(0, 1);
            // ��β�ַ�
            String endstr = source.substring(source.length() - 1, source
                    .length());
            // ��β�ַ���Ҫƥ���������ʽ
            String beginpstr = "[\u4E00-\u9FA5a-zA-Z0-9(]";
            String endpstr = "[\u4E00-\u9FA5a-zA-Z0-9)]";
            String middlepstr = "[\u4E00-\u9FA5a-zA-Z0-9()\\.\\-]";

            Pattern pbegin = Pattern.compile(beginpstr);
            Pattern pend = Pattern.compile(endpstr);
            Pattern pmiddle = Pattern.compile(middlepstr);

            Matcher mbegin = pbegin.matcher(beginstr);
            Matcher mend = pend.matcher(endstr);

            beginYanZheng = mbegin.find();
            endYanZheng = mend.find();
            // System.out.println(beginYanZheng);
            // System.out.println(endYanZheng);
            // System.out.println(beginstr);
            // System.out.println(endstr);
            // �м���֤
            String middlestr = source.substring(1, source.length() - 1);
            char[] array = middlestr.toCharArray();
            for (int i = 0; i < array.length; i++) {
                Matcher mmiddle = pmiddle.matcher(array[i] + "");
                if (!mmiddle.find()) {
                    middleYanZheng = false;
                }
            }
            if (beginYanZheng && endYanZheng && middleYanZheng) {
                String p = "[\\.\\-()]{2,}";
                Pattern teshu = Pattern.compile(p);
                Matcher matcherteshu = teshu.matcher(source);
                // ��ƥ��"-- .. -. �ȵ�"�򷵻�false
                if (matcherteshu.find()) {
                    result = false;
                    return result;
                }
                /**
                 * ������(��������)
                 */
                if (source.contains("(")) {
                    if (source.contains(")")
                            && source.indexOf("(") < source.indexOf(")")) {
                        result = true;
                    } else {
                        result = false;
                        return result;
                    }
                }
                if (source.contains(")")) {
                    if (source.contains("(")
                            && source.indexOf("(") < source.indexOf(")")) {
                        result = true;
                    } else {
                        result = false;
                        return result;
                    }
                } else {
                    result = true;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return result;
    }

	
	

}
