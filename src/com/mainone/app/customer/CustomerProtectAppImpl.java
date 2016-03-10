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
 * @author：zzf
 * 创建时间：Jul 9, 2013 10:29:29 AM
 * 类说明：客户保护
 */
public class CustomerProtectAppImpl implements CustomerProtectApp {
	
	private IBaseServiceDao iBaseServiceDaoA;
	public void setIBaseServiceDaoA(IBaseServiceDao baseServiceDaoA) {
		iBaseServiceDaoA = baseServiceDaoA;
	}
	
	
	/**
	 * 获取单个客户信息
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
	 * 验证客户电话
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
	 * 添加我的保护客户
	 * @param sci
	 * @return
	 * @throws Exception
	 */
	public int save(SfaCustomerInfo sci, SfaLinkManInfo sli) throws Exception{
		
		int id = 0;
		try {
			//添加客户
			iBaseServiceDaoA.saveObject(sci);
			id = sci.getCustomerId();
			//添加领用流水
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
			//添加联系人
			sli.setCustomerId(sci.getCustomerId());
			iBaseServiceDaoA.saveObject(sli);
			} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return id;
	}
	// 验证用户格式
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
            // 首字符
            String beginstr = source.substring(0, 1);
            // 结尾字符
            String endstr = source.substring(source.length() - 1, source
                    .length());
            // 首尾字符需要匹配的正则表达式
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
            // 中间验证
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
                // 若匹配"-- .. -. 等等"则返回false
                if (matcherteshu.find()) {
                    result = false;
                    return result;
                }
                /**
                 * 若包含(则必须包含)
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
