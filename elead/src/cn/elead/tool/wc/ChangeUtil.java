package cn.elead.tool.wc;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import wt.change2.ChangeException2;
import wt.change2.ChangeHelper2;
import wt.change2.ChangeNoticeComplexity;
import wt.change2.ChangeOrder2;
import wt.change2.WTChangeActivity2;
import wt.change2.WTChangeIssue;
import wt.change2.WTChangeOrder2;
import wt.change2.WTChangeRequest2;
import wt.fc.Persistable;
import wt.fc.PersistenceHelper;
import wt.fc.QueryResult;
import wt.fc.WTObject;
import wt.fc.collections.WTArrayList;
import wt.fc.collections.WTList;
import wt.lifecycle.LifeCycleServerHelper;
import wt.lifecycle.State;
import wt.log4j.LogR;
import wt.method.RemoteAccess;
import wt.method.RemoteMethodServer;
import wt.pds.StatementSpec;
import wt.query.QuerySpec;
import wt.query.SearchCondition;
import wt.query.WhereExpression;
import wt.session.SessionServerHelper;
import wt.type.TypeDefinitionReference;
import wt.type.TypedUtility;
import wt.util.WTException;
import wt.util.WTPropertyVetoException;

public class ChangeUtil implements RemoteAccess,Serializable {
	
	/**
	 * this ChangeUtil includes create,get issue,ecr,eca,ecn,and some basic things of change
	 * @author WangY
	 */
	private static final long serialVersionUID = 1L;
	private static String CLASSNAME = ChangeUtil.class.getName();
	private static Logger logger = LogR.getLogger(CLASSNAME);
	
	/**
	 * judge whether issue exist by part number
	 * @param issueNumber
	 * @return	if issueNumber exist in windChill, return true;	such as: issueNumber = "00001"
     * 				else return false;	such as: issueNumber = "asd" or issueNumber = ""  or issueNumber = null
	 */
    public static boolean isIssueExist(String issueNumber) {
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (boolean) RemoteMethodServer.getDefault().invoke("isIssueExist", ChangeUtil.class.getName(),
    					null, new Class[] { String.class }, new Object[] { issueNumber });
 	        } else {
 	        	boolean flag = false;
 	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
 	        	try {
 	        		if (!StringUtils.isEmpty(issueNumber)) {
		 	        	QuerySpec qs = new QuerySpec(WTChangeIssue.class);
						WhereExpression  we = new SearchCondition(WTChangeIssue.class, WTChangeIssue.NUMBER, SearchCondition.EQUAL, issueNumber);
						qs.appendWhere(we, new int[] { 0 });
						QueryResult qr = PersistenceHelper.manager.find((StatementSpec) qs);
						if (qr.hasMoreElements()) {
							flag = true;
						}
 	        		}
 	        	} catch(WTException e) {
 	        		logger.error(CLASSNAME+".isIssueExist:"+e);
 	        	} finally {
 	               SessionServerHelper.manager.setAccessEnforced(enforce);
 	            }
 	        	return flag;
 	        }
         } catch (RemoteException e) {
             logger.error(e.getMessage(),e);
         } catch (InvocationTargetException e) {
         	logger.error(e.getMessage(),e);
         }
         return false;
    }
	
    /**
	 * judge whether ecr exist by ecrNumber
	 * @param ecrNumber
	 * @return	if ecrNumber exist in windChill, return true;	such as: ecrNumber = "00001"
     * 				else return false;	such as: ecrNumber = "asd" or ecrNumber = ""  or ecrNumber = null
	 */
    public static boolean isECRExist(String ecrNumber) {
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (boolean) RemoteMethodServer.getDefault().invoke("isECRExist", ChangeUtil.class.getName(),
    					null, new Class[] { String.class }, new Object[] { ecrNumber });
 	        } else {
 	        	boolean flag = false;
 	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
 	        	try {
 	        		if (!StringUtils.isEmpty(ecrNumber)) {
		 	        	QuerySpec qs = new QuerySpec(WTChangeRequest2.class);
						WhereExpression  we = new SearchCondition(WTChangeRequest2.class, WTChangeRequest2.NUMBER, SearchCondition.EQUAL, ecrNumber);
						qs.appendWhere(we, new int[] { 0 });
						QueryResult qr = PersistenceHelper.manager.find((StatementSpec) qs);
						if (qr.hasMoreElements()) {
							flag = true;
						}
 	        		}
 	        	} catch(WTException e) {
 	        		logger.error(CLASSNAME+".isECRExist:"+e);
 	        	} finally {
 	        		SessionServerHelper.manager.setAccessEnforced(enforce);
 	            }
				return  flag;
 	        }
         } catch (RemoteException e) {
             logger.error(e.getMessage(),e);
         } catch (InvocationTargetException e) {
         	logger.error(e.getMessage(),e);
         }
         return false;
    }
    
	/**
	 * judge whether ecn exist by ecnNumber
	 * @param ecnNumber
	 * @return	if ecnNumber exist in windChill, return true;	such as: ecnNumber = "00001"
     * 				else return false;	such as: ecnNumber = "asd" or ecnNumber = ""  or ecnNumber = null
	 */
    public static boolean isECNExist(String ecnNumber) {
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (boolean) RemoteMethodServer.getDefault().invoke("isECNExist", ChangeUtil.class.getName(),
    					null, new Class[] { String.class }, new Object[] { ecnNumber });
 	        } else {
 	        	boolean flag = false;
 	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
 	        	try {
 	        		if (!StringUtils.isEmpty(ecnNumber)) {
		 	        	QuerySpec qs = new QuerySpec(WTChangeOrder2.class);
						WhereExpression  we = new SearchCondition(WTChangeOrder2.class, WTChangeOrder2.NUMBER, SearchCondition.EQUAL, ecnNumber);
						qs.appendWhere(we, new int[] { 0 });
						QueryResult qr = PersistenceHelper.manager.find((StatementSpec) qs);
						if (qr.hasMoreElements()) {
							flag = true;
						}
 	        		}
 	        	} catch(WTException e) {
 	        		logger.error(CLASSNAME+".isECNExist:"+e);
 	        	} finally {
 	               SessionServerHelper.manager.setAccessEnforced(enforce);
 	           }
 	        	return flag;
 	        }
         } catch (RemoteException e) {
             logger.error(e.getMessage(),e);
         } catch (InvocationTargetException e) {
         	logger.error(e.getMessage(),e);
         }
         return false;
    }
    
    /**
	 * judge whether eca exist by ecaNumber
	 * @param ecaNumber
	 * @return	if ecaNumber exist in windChill, return true;	such as: ecaNumber = "00061"
     * 				else return false;	such as: ecaNumber = "asd" or ecaNumber = ""  or ecaNumber = null
	 */
    public static boolean isECAExist(String ecaNumber) {
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (boolean) RemoteMethodServer.getDefault().invoke("isECNAxist", ChangeUtil.class.getName(),
    					null, new Class[] { String.class }, new Object[] { ecaNumber });
 	        } else {
 	        	boolean flag = false;
 	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
 	        	try {
 	        		if (!StringUtils.isEmpty(ecaNumber)) {
		 	        	QuerySpec qs = new QuerySpec(WTChangeActivity2.class);
						WhereExpression  we = new SearchCondition(WTChangeActivity2.class,WTChangeActivity2.NUMBER, 
								SearchCondition.EQUAL, ecaNumber);
						qs.appendWhere(we,new int[] { 0 });
						QueryResult qr = PersistenceHelper.manager.find((StatementSpec) qs);
						if (qr.hasMoreElements()) {
							flag = true;
						}
 	        		}
 	        	} catch(WTException e) {
 	        		logger.error(CLASSNAME+".isECAExist:"+e);
 	        	} finally {
 	               SessionServerHelper.manager.setAccessEnforced(enforce);
 	            }
 	        	return flag;
 	        }
         } catch (RemoteException e) {
             logger.error(e.getMessage(),e);
         } catch (InvocationTargetException e) {
         	logger.error(e.getMessage(),e);
         }
         return false;
    }
	
    /**
	 * judge whether WTObject exist in windChill
	 * @param object
	 * @return		if object exist in windChill, return true;
     * 				else if object is not exist or object is null,return false;	
	 */
	public static boolean isChangeObjectExist(WTObject object) {
		try {
			if (!RemoteMethodServer.ServerFlag) {
				return (boolean) RemoteMethodServer.getDefault().invoke("isChangeObjectExist", ChangeUtil.class.getName(),
						null, new Class[] { WTObject.class }, new Object[] { object });
	        } else {
	        	boolean flag = false;
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
	        	try {
	        		if (object == null) {
	        			return false;
	        		}
        			QuerySpec qs = null;
        			WhereExpression we = null;
        			if (object instanceof WTChangeIssue) {
        				WTChangeIssue issue = (WTChangeIssue)object;
        				qs = new QuerySpec(WTChangeIssue.class);
						we = new SearchCondition(WTChangeIssue.class,WTChangeIssue.NUMBER, SearchCondition.EQUAL, issue.getNumber());
        			} else if (object instanceof WTChangeRequest2) {
        				WTChangeRequest2 ecr = (WTChangeRequest2)object;
        				qs = new QuerySpec(WTChangeRequest2.class);
						we = new SearchCondition(WTChangeRequest2.class,WTChangeRequest2.NUMBER, SearchCondition.EQUAL, ecr.getNumber());
        			} else if (object instanceof WTChangeOrder2) {
        				WTChangeOrder2 ecr = (WTChangeOrder2)object;
        				qs = new QuerySpec(WTChangeOrder2.class);
						we = new SearchCondition(WTChangeOrder2.class,WTChangeOrder2.NUMBER, SearchCondition.EQUAL, ecr.getNumber());
        			} else if (object instanceof WTChangeActivity2) {
        				WTChangeActivity2 ecr = (WTChangeActivity2)object;
        				qs = new QuerySpec(WTChangeActivity2.class);
						we = new SearchCondition(WTChangeActivity2.class,WTChangeActivity2.NUMBER, SearchCondition.EQUAL, ecr.getNumber());
        			}
					qs.appendWhere(we,new int[] { 0 });
					QueryResult qr = PersistenceHelper.manager.find((StatementSpec) qs);
					if (qr.hasMoreElements()) {
						flag = true;
					}
	        	} catch(WTException e) {
	        		logger.error(CLASSNAME+".isChangeObjectExist:"+e);
	        	} finally {
	                SessionServerHelper.manager.setAccessEnforced(enforce);
	            }
	        	return flag;
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
        return false;
   }
    
	/**
	 * create WTchangeOrder2
	 * @param ecnType
	 * @param number
	 * @param name
	 * @param changeNoticeComplexity
	 * @param description
	 * @return		ecnType,name and changeNoticeComplexity is not exist,encTypy and changeNoticeComplexity has their form
	 * 				if number is empty or number is exist in windChill,it can Automatically generated
	 */
    public static WTChangeOrder2 createWTChangeOrder2(String ecnType,String number, String name,
            ChangeNoticeComplexity changeNoticeComplexity, String description) {
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (WTChangeOrder2) RemoteMethodServer.getDefault().invoke("createWTChangeOrder2", ChangeUtil.class.getName(),
    					null, new Class[] { String.class, String.class, String.class, ChangeNoticeComplexity.class, String.class },
	                		new Object[] { ecnType, number, name, changeNoticeComplexity, description });
	        } else {
	        	WTChangeOrder2 ecn = null;
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
		    	try {
		    		if (!StringUtils.isEmpty(ecnType) && !StringUtils.isEmpty(name) && ecnType.indexOf("wt.change2.WTChangeOrder2")!=-1 && 
		    				changeNoticeComplexity!=null && (changeNoticeComplexity.toString().indexOf("BASIC")!=-1 ||
		    						changeNoticeComplexity.toString().indexOf("SIMPLE")!=-1 || 
		    						changeNoticeComplexity.toString().indexOf("COMPLEX")!=-1)) {
		    			ecn = WTChangeOrder2.newWTChangeOrder2();
		    			TypeDefinitionReference typeDefinitionRef = TypedUtility.getTypeDefinitionReference(ecnType);
		    			ecn.setTypeDefinitionReference(typeDefinitionRef);
			    		if (!StringUtils.isEmpty(number) && !isECNExist(number.toUpperCase())) {
			    			ecn.setNumber(number);
			    		}
			    		ecn.setName(name);
				        if (!StringUtils.isEmpty(description)) {
				        	ecn.setDescription(description);
				        }
				        ecn.setChangeNoticeComplexity(changeNoticeComplexity);
				        ecn = (WTChangeOrder2) ChangeHelper2.service.saveChangeOrder(ecn);
		    		} 
		        } catch(WTException e) {
		        	logger.error(CLASSNAME+".createWTChangeOrder2:"+e);
		        } catch(WTPropertyVetoException e) {
		        	logger.error(CLASSNAME+".createWTChangeOrder2:"+e);
		        } finally {
		            SessionServerHelper.manager.setAccessEnforced(enforce);
		        }
		    	return ecn;
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
        return null;
    }
    
    /**
	 * create WTChangeIssue
	 * @param prType
	 * @param number
	 * @param name
	 * @param description
	 * @return		if prType is WTChangeIssue Type,number is not exist in windChill,and name is not empty or null,return WTChangeIssue Object
	 * 				else return null
	 * 						if number is empty or number is exist in windChill,it can Automatically generated
	 */
    public static WTChangeIssue createWTChangeIssue(String prType, String number, String name, String description) {
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (WTChangeIssue) RemoteMethodServer.getDefault().invoke("createWTChangeIssue", ChangeUtil.class.getName(),null, 
    					new Class[] { String.class, String.class, String.class, String.class},new Object[] { prType, number, name, description});
	        } else {
	        	WTChangeIssue changeIssue = null;
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
		    	try {
		    		if (!StringUtils.isEmpty(prType) && !StringUtils.isEmpty(name) && prType.indexOf("wt.change2.WTChangeIssue")!=-1) {
		    			changeIssue = WTChangeIssue.newWTChangeIssue();
		    			TypeDefinitionReference typeDefinitionRef = TypedUtility.getTypeDefinitionReference(prType);
		    			changeIssue.setTypeDefinitionReference(typeDefinitionRef);
			    		if (!StringUtils.isEmpty(number) && !isIssueExist(number.toUpperCase())) {
			    			changeIssue.setNumber(number);
			    		}
			    		changeIssue.setName(name);
				        if (!StringUtils.isEmpty(description)) {
				        	changeIssue.setDescription(description);
				        }
				        changeIssue = (WTChangeIssue) ChangeHelper2.service.saveChangeIssue(changeIssue);
		    		} 
		        } catch(WTException e) {
		        	logger.error(CLASSNAME+".createWTChangeIssue:"+e);
		        } catch(WTPropertyVetoException e) {
		        	logger.error(CLASSNAME+".createWTChangeIssue:"+e);
		        } finally {
		            SessionServerHelper.manager.setAccessEnforced(enforce);
		        }
		    	return changeIssue;
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
        return null;
    }
    
    /**
	 * create WTChangeRequest2
	 * @param ecrType
	 * @param number
	 * @param name
	 * @param description
	 * @return		if ecrType is WTChangeRequest2 Type,number is not exist in windChill,and name is not empty or null,return WTChangeRequest2 Object
	 * 				else return null
	 * 						if number is empty or number is exist in windChill,it can Automatically generated
	 */
    public static WTChangeRequest2 createWTChangeRequest2(String ecrType, String number, String name, String description){
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (WTChangeRequest2) RemoteMethodServer.getDefault().invoke("createWTChangeRequest2", ChangeUtil.class.getName(), null, 
    					new Class[] { String.class, String.class, String.class, String.class}, new Object[] { ecrType, number, name, description });
	        } else {
	        	WTChangeRequest2 ecr = null;
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
		    	try {
		    		if (!StringUtils.isEmpty(ecrType) && !StringUtils.isEmpty(name) && ecrType.indexOf("wt.change2.WTChangeRequest2")!=-1) {
		    			ecr = WTChangeRequest2.newWTChangeRequest2();
		    			TypeDefinitionReference typeDefinitionRef = TypedUtility.getTypeDefinitionReference(ecrType);
		    			ecr.setTypeDefinitionReference(typeDefinitionRef);
			    		if (!StringUtils.isEmpty(number) && !isECRExist(number.toUpperCase())) {
			    			ecr.setNumber(number);
			    		}
			    		ecr.setName(name);
				        if (!StringUtils.isEmpty(description)) {
				        	ecr.setDescription(description);
				        }
				        ecr = (WTChangeRequest2) ChangeHelper2.service.saveChangeRequest(ecr);
		    		} 
		        } catch(WTException e) {
		        	logger.error(CLASSNAME+".createWTChangeRequest2:"+e);
		        } catch(WTPropertyVetoException e) {
		        	logger.error(CLASSNAME+".createWTChangeRequest2:"+e);
		        } finally {
		            SessionServerHelper.manager.setAccessEnforced(enforce);
		        }
		    	return ecr;
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
        return null;
    }
    
    /**
     * create WTChangeActivity2
     * @param ecaType
     * @param number
     * @param name
     * @param description
     * @param changeOrder
     * @return		if ecaType is WTChangeActivity2 Type,number is not exist in windChill,changeOrder is exist in windChill
     * 					and name is not empty or null,return WTChangeActivity2 Object
	 * 				else return null
	 * 						if number is empty or number is exist in windChill,it can Automatically generated
     */
    public static WTChangeActivity2 createWTChangeActivity2(String ecaType, String number, String name, String description,
    		WTChangeOrder2 changeOrder){
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (WTChangeActivity2) RemoteMethodServer.getDefault().invoke("createWTChangeActivity2", ChangeUtil.class.getName(), null, 
    					new Class[] { String.class, String.class, String.class, String.class, WTChangeOrder2.class }, new Object[] { ecaType, 
    					number, name, description, changeOrder });
	        } else {
	        	WTChangeActivity2 eca = null;
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
		    	try {
		    		if (changeOrder == null) {
		    			return null;
		    		}
		    		if (!StringUtils.isEmpty(ecaType) && !StringUtils.isEmpty(name) &&
		    				ecaType.indexOf("wt.change2.WTChangeActivity2")!=-1) {
		    			eca = WTChangeActivity2.newWTChangeActivity2();
		    			TypeDefinitionReference typeDefinitionRef = TypedUtility.getTypeDefinitionReference(ecaType);
		    			eca.setTypeDefinitionReference(typeDefinitionRef);
			    		if (!StringUtils.isEmpty(number) && !isECAExist(number.toUpperCase())) {
			    			eca.setNumber(number);
			    		}
			    		eca.setName(name);
				        if (!StringUtils.isEmpty(description)) {
				        	eca.setDescription(description);
				        }
				        eca = (WTChangeActivity2) ChangeHelper2.service.saveChangeActivity(changeOrder, eca);
		    		} 
		        } catch(WTException e) {
		        	logger.error(CLASSNAME+".createWTChangeActivity2:"+e);
		        } catch(WTPropertyVetoException e) {
		        	logger.error(CLASSNAME+".createWTChangeActivity2:"+e);
		        } finally {
		            SessionServerHelper.manager.setAccessEnforced(enforce);
		        }
		    	return eca;
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
        return null;
    }
    
    /**
     * This method is used to get ECN by it's number.
     * @param number
     * @return		if number is exist,return ecn
     * 				else if number is not exist,return null
     * 				else if number is empty or number is null,return null
     */
	public static WTChangeOrder2 getECNByNumber(String number){
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (WTChangeOrder2) RemoteMethodServer.getDefault().invoke("getECNByNumber", ChangeUtil.class.getName(), 
    					null, new Class[] { String.class }, new Object[] { number });
	        } else {
	        	WTChangeOrder2 ecn = null;
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
		        try {
		        	if (!StringUtils.isEmpty(number) && isECNExist(number)) {
			            QuerySpec criteria = new QuerySpec(WTChangeOrder2.class);
			            criteria.appendSearchCondition(new SearchCondition(WTChangeOrder2.class, WTChangeOrder2.NUMBER,
			                    SearchCondition.EQUAL, number, false));
			            QueryResult results = PersistenceHelper.manager.find(criteria);
			            if (results.hasMoreElements()) {
			                ecn = (WTChangeOrder2) results.nextElement();
			            }
		        	}
		        } catch (WTException e) {
		        	logger.error(CLASSNAME+".getECNByNumber:"+e);
		        } finally {
		            SessionServerHelper.manager.setAccessEnforced(enforce);
		        }
		        return ecn;
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
        return null;
    }
    
    /**
     * get all affected object from ECN
     * 
     * @param ecn
     * @return	if ecn exist and ecn has before objects,return WTObjects
     * 			else if ecn is not exist,return null
     * 			else if ecn is empty or ecn is null,return null
     * @throws ChangeException2
     * @throws WTException
     */
	public static List getChangeBefore(WTChangeOrder2 ecn) {
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (List) RemoteMethodServer.getDefault().invoke("getChangeBefore", ChangeUtil.class.getName(),
    					null, new Class[] { WTChangeOrder2.class }, new Object[] { ecn });
	        } else {
	        	List list = new ArrayList();
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
	        	try {
	        		if (ecn ==  null) {
	        			return list;
	        		}
        			QueryResult result = ChangeHelper2.service.getChangeActivities(ecn);
			        while (result.hasMoreElements()) {
			            WTChangeActivity2 activity = (WTChangeActivity2) result.nextElement();
			            QueryResult res = ChangeHelper2.service.getChangeablesBefore(activity);
			            while (res.hasMoreElements()) {
			                WTObject object = (WTObject) res.nextElement();
			                list.add(object);
			            }
			        }
	        	} catch(WTException e) {
	        		logger.error(CLASSNAME+".getChangeBefore:"+e);
	        	} finally {
	                SessionServerHelper.manager.setAccessEnforced(enforce);
	            }
	        	return list;
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
        return null;
    }
    
    /**
     * get all resulting object from ECN
     * 
     * @param ecn
     * @return		if ecn exist and ecn has after objects,return WTObjects
     * 				else if ecn is not exist,return null
     * 				else if ecn is empty or ecn is null,return null
     * @throws WTException
     */
	public static List<?> getChangeAfter(WTChangeOrder2 ecn) {
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (List<WTObject>) RemoteMethodServer.getDefault().invoke("getChangeAfter", ChangeUtil.class.getName(),
    					null, new Class[] { WTChangeOrder2.class }, new Object[] { ecn });
	        } else {
	        	List<WTObject> list = new ArrayList<WTObject>();
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
	        	try {
	        		if (ecn ==  null) {
	        			return list;
	        		}
        			QueryResult result = ChangeHelper2.service.getChangeActivities(ecn);
				    while (result.hasMoreElements()) {
				    	WTChangeActivity2 activity = (WTChangeActivity2) result.nextElement();
				        QueryResult res = ChangeHelper2.service.getChangeablesAfter(activity);
				        while (res.hasMoreElements()) {
				        	WTObject object = (WTObject) res.nextElement();
				            list.add(object);
				        }
				    }
	        	} catch(WTException e) {
	        		logger.error(CLASSNAME+".getChangeAfter:"+e);
	        	} finally {
	        		SessionServerHelper.manager.setAccessEnforced(enforce);
	        	}
	        	return list;
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
        return null;
    }
    
    /**
     * get PR by issue Number
     * @param number
     * @return		if number is exist,return issue
     * 				else if number is not exist,return null
     * 				else if number is empty or number is null,return null
     */
    @SuppressWarnings("deprecation")
	public static WTChangeIssue getPRByNumber(String number) {
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (WTChangeIssue) RemoteMethodServer.getDefault().invoke("getPRByNumber", ChangeUtil.class.getName(), 
    					null, new Class[] { String.class }, new Object[] { number });
	        } else {
	        	WTChangeIssue pr = null;
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
		        try {
		        	if (!StringUtils.isEmpty(number) && isECRExist(number)) {
		        		QuerySpec criteria = new QuerySpec(WTChangeIssue.class);
			            criteria.appendSearchCondition(new SearchCondition(WTChangeIssue.class, WTChangeIssue.NUMBER, SearchCondition.EQUAL, number, false));
			            QueryResult results = PersistenceHelper.manager.find(criteria);
			            if (results.hasMoreElements()) {
			                pr = (WTChangeIssue) results.nextElement();
			            }
		        	}
		        } catch (WTException e) {
		        	logger.error(CLASSNAME+".getPRByNumber:"+e);
		        } finally {
		            SessionServerHelper.manager.setAccessEnforced(enforce);
		        }
		        return pr;
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
    	return null;
    }
    
    /**
     * get ECA by Number
     * @param number
     * @return		if number is exist,return eca
     * 				else if number is not exist,return null
     * 				else if number is empty or number is null,return null
     */
    @SuppressWarnings("deprecation")
	public static WTChangeActivity2 getECAByNumber(String number) {
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (WTChangeActivity2) RemoteMethodServer.getDefault().invoke("getECAByNumber", ChangeUtil.class.getName(), null, 
    					new Class[] { String.class }, new Object[] { number });
	        } else {
	        	WTChangeActivity2 eca = null;
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
		        try {
		        	if (!StringUtils.isEmpty(number) && isECAExist(number)) {
		        		QuerySpec criteria = new QuerySpec(WTChangeActivity2.class);
			            criteria.appendSearchCondition(new SearchCondition(WTChangeActivity2.class, WTChangeActivity2.NUMBER, 
			            		SearchCondition.EQUAL, number, false));
			            QueryResult results = PersistenceHelper.manager.find(criteria);
			            if (results.hasMoreElements()) {
			                eca = (WTChangeActivity2) results.nextElement();
			            }
		        	}
		        } catch (WTException e) {
		        	logger.error(CLASSNAME+".getECAByNumber:"+e);
		        } finally {
		            SessionServerHelper.manager.setAccessEnforced(enforce);
		        }
		        return eca;
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
        return null;
    }
    
    /**
     * get ECR by number
     * @param number
     * @return		if number is exist,return ecr
     * 				else if number is not exist,return null
     * 				else if number is empty or number is null,return null
     */
    @SuppressWarnings("deprecation")
	public static WTChangeRequest2 getECRByNumber(String number){
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (WTChangeRequest2) RemoteMethodServer.getDefault().invoke("getECRByNumber", ChangeUtil.class.getName(),
    					null, new Class[] { String.class }, new Object[] { number });
	        } else {
	        	WTChangeRequest2  ecr = null;
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
		        try {
		        	if (!StringUtils.isEmpty(number) && isECRExist(number)) {
		        		QuerySpec criteria = new QuerySpec(WTChangeRequest2.class);
			            criteria.appendSearchCondition(new SearchCondition(WTChangeRequest2.class, WTChangeRequest2.NUMBER,
			                    SearchCondition.EQUAL, number, false));
			            QueryResult results = PersistenceHelper.manager.find(criteria);
			            if (results.hasMoreElements()) {
			            	ecr =  (WTChangeRequest2) results.nextElement();
			            }
		        	}
		        } catch (WTException e) {
		        	logger.error(CLASSNAME+".getECRByNumber:"+e);
		        } finally {
		            SessionServerHelper.manager.setAccessEnforced(enforce);
		        }
		        return ecr;
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
        return null;
    }
    
    /**
     * get ECA By ECN
     * @param ecn
     * @return		if ecn is exist,return eca
     * 				else if ecn is not exist,return []
     * 				else if ecn is null,return []
     */
    @SuppressWarnings("unchecked")
	public static List<WTChangeActivity2> getECAByECN(WTChangeOrder2 ecn) {
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (List<WTChangeActivity2>) RemoteMethodServer.getDefault().invoke("getECAByECN", ChangeUtil.class.getName(), 
    					null, new Class[] { WTChangeOrder2.class }, new Object[] { ecn });
	        } else {
	        	List<WTChangeActivity2> list = new ArrayList<WTChangeActivity2>();
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
	        	try {
	        		if (ecn ==  null) {
	        			return list;
	        		}
		    		QueryResult result = ChangeHelper2.service.getChangeActivities(ecn);
	    			while (result.hasMoreElements()) {
	    				WTChangeActivity2 activity = (WTChangeActivity2) result.nextElement();
			            list.add(activity);
			        }
	        	} catch(WTException e) {
	        		logger.error(CLASSNAME+".getECAByECN:"+e);
	        	} finally {
	                SessionServerHelper.manager.setAccessEnforced(enforce);
	            }
	        	return list;
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
    	return null;
    }
    
    /**
     * get ECN by ECR
     * @param ecr
     * @return		if ecr is exist,return ecn
     * 				else if ecr is not exist,return []
     * 				else if ecr is null,return []
     */
    @SuppressWarnings("unchecked")
	public static List<WTChangeOrder2> getECNByECR(WTChangeRequest2 ecr) {
    	try{
    		if (!RemoteMethodServer.ServerFlag) {
    			return (List<WTChangeOrder2>) RemoteMethodServer.getDefault().invoke("getECNByECR", ChangeUtil.class.getName(), 
    					null, new Class[] { WTChangeRequest2.class }, new Object[] { ecr });
	        } else {
	        	List<WTChangeOrder2> list = new ArrayList<WTChangeOrder2>();
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
	        	try {
	        		if (ecr ==  null) {
	        			return list;
	        		}
	    			QueryResult result = ChangeHelper2.service.getChangeOrders(ecr);
	    			while (result.hasMoreElements()) {
	    				WTChangeOrder2 order = (WTChangeOrder2) result.nextElement();
			            list.add(order);
			        }
	        	} catch(WTException e){
	        		logger.error(CLASSNAME+".getECNByECR:"+e);
	        	} finally {
	                SessionServerHelper.manager.setAccessEnforced(enforce);
	            }
	        	return list;
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
    	return null;
    }
    
    /**
     * get ECN by ECA
     * @param eca
     * @return		if eca is exist,return ecn
     * 				else if eca is not exist,return null
     * 				else if eca is null,return null
     */
    public static WTChangeOrder2 getECNByECA(WTChangeActivity2 eca){
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (WTChangeOrder2) RemoteMethodServer.getDefault().invoke("getECNByECA", ChangeUtil.class.getName(), 
    					null, new Class[] { WTChangeActivity2.class }, new Object[] { eca });
	        } else {
	        	WTChangeOrder2 ecn = null;
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
	        	try {
	        		if (eca ==  null) {
	        			return null;
	        		}
	    			QueryResult result = ChangeHelper2.service.getChangeOrder(eca);
	    			while (result.hasMoreElements()) {
	    				ecn = (WTChangeOrder2) result.nextElement();
			        }
	        	} catch(WTException e) {
	        		logger.error(CLASSNAME+".getECNByECA:"+e);
	        	} finally {
	                SessionServerHelper.manager.setAccessEnforced(enforce);
	            }
	        	return ecn;
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
    	return null;
    }
    
    /**
     * get ECR by PR
     * @param issue
     * @return		if issue is exist,return ecr
     * 				else if issue is not exist,return null
     * 				else if issue is null,return null
     */
    public static WTChangeRequest2 getECRByPR(WTChangeIssue issue) {
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (WTChangeRequest2) RemoteMethodServer.getDefault().invoke("getECRByPR", ChangeUtil.class.getName(), 
    					null, new Class[] { WTChangeIssue.class }, new Object[] { issue });
	        } else {
	        	WTChangeRequest2 ecr = null;
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
	        	try {
	        		if (issue ==  null) {
	        			return null;
	        		}
	    			QueryResult result = ChangeHelper2.service.getChangeRequest(issue);
	    			while (result.hasMoreElements()) {
	    				ecr = (WTChangeRequest2) result.nextElement();
			        }
	        	} catch(WTException e) {
	        		logger.error(CLASSNAME+".getECRByPR:"+e);
	        	} finally {
	                SessionServerHelper.manager.setAccessEnforced(enforce);
	            }
	        	return ecr;
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
    	return null;
    }
    
    /**
     * get PR by ECR
     * @param ecr
     * @return		if ecr is exist,return pr
     * 				else if ecr is not exist,return []
     * 				else if ecr is null,return []
     */
    @SuppressWarnings("unchecked")
	public static List<WTChangeIssue> getPRByECR(WTChangeRequest2 ecr) {
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (List<WTChangeIssue>) RemoteMethodServer.getDefault().invoke("getPRByECR", ChangeUtil.class.getName(),
    					null, new Class[] { WTChangeRequest2.class }, new Object[] { ecr });
	        } else {
	        	List<WTChangeIssue> list = new ArrayList<WTChangeIssue>();
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
	        	try {
	        		if (ecr ==  null) {
	        			return list;
	        		}
	    			QueryResult result = ChangeHelper2.service.getChangeIssues(ecr);
	    			while (result.hasMoreElements()) {
	    				WTChangeIssue order = (WTChangeIssue) result.nextElement();
			            list.add(order);
			        }
	        	} catch(WTException e) {
	        		logger.error(CLASSNAME+".getPRByECR:"+e);
	        	} finally {
	                SessionServerHelper.manager.setAccessEnforced(enforce);
	            }
	        	return list;
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
    	return null;
    }
    
    /**
     * get ecr by ecn
     * @param ecn
     * @return		if ecn is exist,return ecr
     * 				else if ecn is not exist,return null
     * 				else if ecn is null,return null
     */
    public static WTChangeRequest2 getECRByECN(WTChangeOrder2 ecn) {
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (WTChangeRequest2) RemoteMethodServer.getDefault().invoke("getECRByECN", ChangeUtil.class.getName(), 
    					null, new Class[] { WTChangeOrder2.class }, new Object[] { ecn });
	        } else {
	        	WTChangeRequest2 ecr = null;
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
	        	try {
	        		if (ecn ==  null) {
	        			return null;
	        		}
	    			QueryResult result = ChangeHelper2.service.getChangeRequest(ecn);
	    			while (result.hasMoreElements()) {
	    				ecr = (WTChangeRequest2) result.nextElement();
			        }
	        	} catch(WTException e) {
	        		logger.error(CLASSNAME+".getECRByECN:"+e);
	        	} finally {
	                SessionServerHelper.manager.setAccessEnforced(enforce);
	            }
	        	return ecr;
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
    	return null;
    }
    
    /**
     * set stateOfAfter by ChangeOrder2 
     * @param order
     * @param state
     * 			if order is exist and state is exist in windChill,set the after object state
     * 			else if order is not exist,there is nothing to do
     * 			else if order and state are all null,there is nothing to do
     */
    public static void setStateOfAfter(ChangeOrder2 order, State state) {
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			RemoteMethodServer.getDefault().invoke("setStateOfAfter", ChangeUtil.class.getName(), null, 
    					new Class[] { ChangeOrder2.class, State.class }, new Object[] { order, state });
	        } else {
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
	        	try {
	        		if (order == null) {
	        			return;
	        		}
	        		if (state == null) {
	        			return;
	        		}
			        QueryResult qr = ChangeHelper2.service.getChangeablesAfter(order);
			        WTList changeables = new WTArrayList(qr);
			        LifeCycleServerHelper.service.setState(changeables, state);
	        	} catch(WTException e){
	        		logger.error(CLASSNAME+".setStateOfAfter:"+e);
	        	} finally {
	                SessionServerHelper.manager.setAccessEnforced(enforce);
	            }
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
    }
    
    /**
     * setStateOfBefore by ChangeOrder2
     * @param order
     * @param state
     * @throws WTException
     * 		if order is exist and state is exist in windChill,set the before object state
     * 			else if order is not exist,there is nothing to do
     * 			else if order and state are all null,there is nothing to do
     */
    public static void setStateOfBefore(ChangeOrder2 order, State state) throws WTException {
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			RemoteMethodServer.getDefault().invoke("setStateOfBefore", ChangeUtil.class.getName(), null, new Class[] 
    					{ ChangeOrder2.class, State.class }, new Object[] { order, state });
	        } else {
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
	        	try {
	        		if (order == null) {
	        			return;
	        		}
	        		if (state == null) {
	        			return;
	        		}
			        QueryResult qr = ChangeHelper2.service.getChangeablesBefore(order);
			        WTList changeables = new WTArrayList(qr);
			        LifeCycleServerHelper.service.setState(changeables, state);
	        	} catch(WTException e) {
	        		logger.error(CLASSNAME+".setStateOfBefore:"+e);
	        	} finally {
	                SessionServerHelper.manager.setAccessEnforced(enforce);
	            }
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
    }
   
    /**
     * judge whether the object has been existend in  the result item
     * @param persistable
     * @param eca
     * @return		if eca is exist and eca has after object(persistable),return true
     * 				else if eca is not exist,reutrn false
     * 				else if eca is exist,persistable is not exist,return false
     * 				else if eca and persistable are null,return false
     */
    public static boolean inChangeablesAfter(Persistable persistable, WTChangeActivity2 eca) {
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (boolean)RemoteMethodServer.getDefault().invoke("inChangeablesAfter", ChangeUtil.class.getName(), null, 
    					new Class[] { Persistable.class, WTChangeActivity2.class }, new Object[] { persistable, eca });
	        } else {
	        	boolean flag = false;
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
				try {
					if (persistable == null) {
						return false;
					}
					if (eca == null) {
						return false;
					}
					QueryResult caResult = ChangeHelper2.service.getChangeablesAfter(eca);
					while (caResult.hasMoreElements()) {
					    Persistable persistableAfter = (Persistable) caResult.nextElement();
					    flag = persistable.equals(persistableAfter);
					    if (flag) {
					        break;
					    }
					}
				} catch(WTException e) {
					logger.error(CLASSNAME+".inChangeablesAfter:"+e);
				} finally {
		            SessionServerHelper.manager.setAccessEnforced(enforce);
		        }
				return flag;
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
		return false;
	}
    
    /**
     * judge whether the object has been existend in  the affect item
     * @param persistable
     * @param eca
     * @return		if eca is exist and eca has before object(persistable),return true
     * 				else if eca is not exist,reutrn false
     * 				else if eca is exist,persistable is not exist,return false
     * 				else if eca and persistable are null,return false
     */
    public static boolean inChangeablesBefore(Persistable persistable, WTChangeActivity2 eca) {
    	try {
    		if (!RemoteMethodServer.ServerFlag) {
    			return (boolean)RemoteMethodServer.getDefault().invoke("inChangeablesBefore", ChangeUtil.class.getName(), 
    					null, new Class[] { Persistable.class, WTChangeActivity2.class }, new Object[] { persistable, eca });
	        } else {
	        	boolean flag = false;
	        	boolean enforce = SessionServerHelper.manager.setAccessEnforced(false);
				try {
					if (persistable == null) {
						return false;
					}
					if (eca == null) {
						return false;
					}
					QueryResult caResult = ChangeHelper2.service.getChangeablesBefore(eca);
					while (caResult.hasMoreElements()) {
						Persistable persistableAfter = (Persistable) caResult.nextElement();
					    flag = persistable.equals(persistableAfter);
					    if (flag) {
					    	break;
					    }
					}
				} catch(WTException e) {
					logger.error(CLASSNAME+".inChangeablesBefore:"+e);
				} finally {
		            SessionServerHelper.manager.setAccessEnforced(enforce);
		        }
				return flag;
	        }
        } catch (RemoteException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(),e);
        }
		return false;
	}
    
	public static void test() throws RemoteException, InvocationTargetException, WTException{
//		System.out.println("/*********************isIssueExist********************/");
//		System.out.println(isIssueExist("00001"));
//		System.out.println(isIssueExist("asd"));
//		System.out.println(isIssueExist(""));
//		System.out.println(isIssueExist(null));
//		System.out.println("/*********************isECRExist********************/");
//		System.out.println(isECRExist("00001"));
//		System.out.println(isECRExist("asd"));
//		System.out.println(isECRExist(""));
//		System.out.println(isECRExist(null));
//		System.out.println("/*********************isECNExist********************/");
//		System.out.println(isECNExist("00061"));
//		System.out.println(isECNExist("asd"));
//		System.out.println(isECNExist(""));
//		System.out.println(isECNExist(null));
//		System.out.println("/*********************isECRExist********************/");
//		System.out.println(isECAExist("00001"));
//		System.out.println(isECAExist("asd"));
//		System.out.println(isECAExist(""));
//		System.out.println(isECAExist(null));
//		System.out.println("/*********************isChangeObjectExist********************/");
//		System.out.println(isChangeObjectExist(getPRByNumber("00001")));
//		System.out.println(isChangeObjectExist(getPRByNumber("asd")));
//		System.out.println(isChangeObjectExist(getPRByNumber("")));
//		System.out.println(isChangeObjectExist(getPRByNumber(null)));
//		System.out.println("-----------------------------------------");
//		System.out.println(isChangeObjectExist(getECRByNumber("00001")));
//		System.out.println(isChangeObjectExist(getECRByNumber("asd")));
//		System.out.println(isChangeObjectExist(getECRByNumber("")));
//		System.out.println(isChangeObjectExist(getECRByNumber(null)));
//		System.out.println("-----------------------------------------");
//		System.out.println(isChangeObjectExist(getECNByNumber("00001")));
//		System.out.println(isChangeObjectExist(getECNByNumber("asd")));
//		System.out.println(isChangeObjectExist(getECNByNumber("")));
//		System.out.println(isChangeObjectExist(getECNByNumber(null)));
//		System.out.println("-----------------------------------------");
//		System.out.println(isChangeObjectExist(getECAByNumber("00001")));
//		System.out.println(isChangeObjectExist(getECAByNumber("asd")));
//		System.out.println(isChangeObjectExist(getECAByNumber("")));
//		System.out.println(isChangeObjectExist(getECAByNumber(null)));
//		System.out.println("/*********************createWTChangeOrder2********************/");
//		System.out.println(createWTChangeOrder2("wt.change2.WTChangeOrder2", "test_change_number1", 
//				"test_change_name1",ChangeNoticeComplexity.BASIC,"createChange1"));
//		System.out.println(createWTChangeOrder2("wt.change2.WTChangeOrder2", "", 
//				"test_change_name2",ChangeNoticeComplexity.BASIC, "createChange1"));
//		System.out.println(createWTChangeOrder2("wt.change2.WTChangeOrder2", "00061", 
//				"test_change_name3",ChangeNoticeComplexity.BASIC, "createChange1"));
//		System.out.println(createWTChangeOrder2("wt.change2", "test_change_number1", 
//				"test_change_name4",ChangeNoticeComplexity.BASIC, "createChange1"));
//		System.out.println(createWTChangeOrder2("wt.change2.WTChangeOrder2", "test_change_number5",
//				"test_change_name5",null, "createChange1"));
//		System.out.println(createWTChangeOrder2("wt.change2.WTChangeOrder2", "test_change_number1",
//				"",null, "createChange1"));
//		System.out.println(createWTChangeOrder2("wt.change2.WTChangeOrder2", "test_change_number7", 
//				"test_change_name7",ChangeNoticeComplexity.BASIC,""));
//		System.out.println(createWTChangeOrder2(null, null, null,null,null));
//		System.out.println("/*********************createWTChangeIssue********************/");
//		System.out.println(createWTChangeIssue("wt.change2.WTChangeIssue", "issue-number001", "issue-name001", "create WTChangeIssue"));
//		System.out.println(createWTChangeIssue("wt.change2.WTChangeIssue", "", "issue-name001", "create WTChangeIssue"));
//		System.out.println(createWTChangeIssue("wt.change2", "issue-number002", "issue-name001", "create WTChangeIssue"));
//		System.out.println(createWTChangeIssue("wt.change2.WTChangeIssue", "issue-number002", "issue-name001", ""));
//		System.out.println(createWTChangeIssue(null, null, null, null));
//		System.out.println("/*********************createWTChangeRequest2********************/");
//		System.out.println(createWTChangeRequest2("wt.change2.WTChangeRequest2", "request-number001", "request-name001", "create WTChangeRequest2"));
//		System.out.println(createWTChangeRequest2("wt.change2.WTChangeRequest2", "", "request-name001", "create WTChangeRequest2"));
//		System.out.println(createWTChangeRequest2("wt.change2", "request-number002", "request-name001", "create WTChangeRequest2"));
//		System.out.println(createWTChangeRequest2("wt.change2.WTChangeRequest2", "request-number002", "request-name001", ""));
//		System.out.println(createWTChangeRequest2(null, null, null, null));
//		System.out.println("/*********************createWTChangeRequest2********************/");
//		System.out.println(createWTChangeActivity2("wt.change2.WTChangeActivity2", "activity-number002", "activity-name002", "create WTChangeActivity2",getECNByNumber("00068")));
//		System.out.println(createWTChangeActivity2("wt.change2.WTChangeActivity2", "activity-number002", "activity-name002", "create WTChangeActivity2",getECNByNumber("00068")));
//		System.out.println(createWTChangeActivity2("wt.change2.WTChangeActivity2", "", "activity-name002", "create WTChangeActivity2",getECNByNumber("00068")));
//		System.out.println(createWTChangeActivity2("wt.change2", "activity-number002", "activity-name002", "create WTChangeActivity2",getECNByNumber("00068")));
//		System.out.println(createWTChangeActivity2("wt.change2.WTChangeActivity2", "activity-number002", "activity-name002", "create WTChangeActivity2",getECNByNumber("asd")));
//		System.out.println(createWTChangeActivity2("wt.change2.WTChangeActivity2", "activity-number002", "", "create WTChangeActivity2",getECNByNumber("00068")));
//		System.out.println(createWTChangeActivity2(null, null, null, null,null));
//		System.out.println(createWTChangeRequest2("wt.change2.WTChangeRequest2", "", "request-name001", "create WTChangeRequest2"));
//		System.out.println(createWTChangeRequest2("wt.change2", "request-number002", "request-name001", "create WTChangeRequest2"));
//		System.out.println(createWTChangeRequest2("wt.change2.WTChangeRequest2", "request-number002", "request-name001", ""));
//		System.out.println(createWTChangeRequest2(null, null, null, null));
//		System.out.println("/*********************getECNByNumber********************/");
//		System.out.println(getECNByNumber("00061"));
//		System.out.println(getECNByNumber("asd"));
//		System.out.println(getECNByNumber(""));
//		System.out.println(getECNByNumber(null));
//		System.out.println("/*********************getChangeBefore********************/");
//		List<WTObject> list = getChangeBefore(getECNByNumber("00001"));
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println("1-------------"+list.get(i));
//		}
//		List<WTObject> list1 = getChangeBefore(getECNByNumber("asd"));
//		for (int i = 0; i < list1.size(); i++) {
//			System.out.println("2-------------"+list1.get(i));
//		}
//		List<WTObject> list2 = getChangeBefore(getECNByNumber(""));
//		for (int i = 0; i < list2.size(); i++) {
//			System.out.println("3-------------"+list2.get(i));
//		}
//		List<WTObject> list3 = getChangeBefore(null);
//		for (int i = 0; i < list3.size(); i++) {
//			System.out.println("4-------------"+list3.get(i));
//		}
//		System.out.println("/*********************getChangeAfter********************/");
//		List<WTObject> list = getChangeAfter(getECNByNumber("00001"));
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println("1-------------"+list.get(i));
//		}
//		List<WTObject> list1 = getChangeAfter(getECNByNumber("asd"));
//		for (int i = 0; i < list1.size(); i++) {
//			System.out.println("2-------------"+list1.get(i));
//		}
//		List<WTObject> list2 = getChangeAfter(getECNByNumber(""));
//		for (int i = 0; i < list2.size(); i++) {
//			System.out.println("3-------------"+list2.get(i));
//		}
//		List<WTObject> list3 = getChangeAfter(null);
//		for (int i = 0; i < list3.size(); i++) {
//			System.out.println("4-------------"+list3.get(i));
//		}
//		System.out.println("/*********************getPRByNumber********************/");
//		System.out.println(getPRByNumber("00001"));
//		System.out.println(getPRByNumber("asd"));
//		System.out.println(getPRByNumber(""));
//		System.out.println(getPRByNumber(null));
//		System.out.println("/*********************getECAByNumber********************/");
//		System.out.println(getECAByNumber("00001"));
//		System.out.println(getECAByNumber("asd"));
//		System.out.println(getECAByNumber(""));
//		System.out.println(getECAByNumber(null));
//		System.out.println("/*********************getECRByNumber********************/");
//		System.out.println(getECRByNumber("00001"));
//		System.out.println(getECRByNumber("asd"));
//		System.out.println(getECRByNumber(""));
//		System.out.println(getECRByNumber(null));
//		System.out.println("/*********************getECAByECN********************/");
//		List<WTChangeActivity2> list = getECAByECN(getECNByNumber("00001"));
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println("1-------------"+list.get(i));
//		}
//		List<WTChangeActivity2> list1 = getECAByECN(getECNByNumber("asd"));
//		for (int i = 0; i < list1.size(); i++) {
//			System.out.println("2-------------"+list1.get(i));
//		}
//		List<WTChangeActivity2> list2 = getECAByECN(null);
//		for (int i = 0; i < list2.size(); i++) {
//			System.out.println("3-------------"+list2.get(i));
//		}
//		System.out.println("/*********************getECAByECN********************/");
//		List<WTChangeOrder2> list = getECNByECR(getECRByNumber("00001"));
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println("1-------------"+list.get(i));
//		}
//		List<WTChangeOrder2> list1 = getECNByECR(getECRByNumber("asd"));
//		for (int i = 0; i < list1.size(); i++) {
//			System.out.println("2-------------"+list1.get(i));
//		}
//		List<WTChangeOrder2> list2 = getECNByECR(null);
//		for (int i = 0; i < list2.size(); i++) {
//			System.out.println("3-------------"+list2.get(i));
//		}
//		System.out.println("/*********************getECNByECA********************/");
//		System.out.println(getECNByECA(getECAByNumber("00001")));
//		System.out.println(getECNByECA(getECAByNumber("asd")));
//		System.out.println(getECNByECA(null));
//		System.out.println("/*********************getECRByPR********************/");
//		System.out.println(getECRByPR(getPRByNumber("00021")));
//		System.out.println(getECRByPR(getPRByNumber("asd")));
//		System.out.println(getECRByPR(null));
//		System.out.println("/*********************getPRByECR********************/");
//		List<WTChangeIssue> list = getPRByECR(getECRByNumber("00001"));
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println("1-------------"+list.get(i));
//		}
//		List<WTChangeIssue> list1 = getPRByECR(getECRByNumber("asd"));
//		for (int i = 0; i < list1.size(); i++) {
//			System.out.println("2-------------"+list1.get(i));
//		}
//		List<WTChangeIssue> list2 = getPRByECR(null);
//		for (int i = 0; i < list2.size(); i++) {
//			System.out.println("3-------------"+list2.get(i));
//		}
//		System.out.println("/*********************getECRByECN********************/");
//		System.out.println(getECRByECN(getECNByNumber("00001")));
//		System.out.println(getECRByECN(getECNByNumber("asd")));
//		System.out.println(getECRByECN(null));
//		System.out.println("/*********************setStateOfAfter********************/");
//		setStateOfAfter(getECNByNumber("00001"), State.RELEASED);
//		setStateOfAfter(getECNByNumber("asd"), State.RELEASED);
//		setStateOfAfter(null, null);
//		System.out.println("/*********************setStateOfBefore********************/");
//		setStateOfBefore(getECNByNumber("00001"), State.RELEASED);
//		setStateOfBefore(getECNByNumber("asd"), State.RELEASED);
//		setStateOfBefore(null, null);
//		System.out.println("/*********************inChangeablesAfter********************/");
//		System.out.println(inChangeablesAfter(WCUtil.getPersistableByOid("OR:wt.part.WTPart:176160"), getECAByNumber("00001")));
//		System.out.println(inChangeablesAfter(WCUtil.getPersistableByOid("OR:wt.part.WTPart:171024"), getECAByNumber("00001")));
//		System.out.println(inChangeablesAfter(null, null));
//		System.out.println("/*********************inChangeablesBefore********************/");
//		System.out.println(inChangeablesBefore(WCUtil.getPersistableByOid("OR:wt.part.WTPart:171024"), getECAByNumber("00001")));
//		System.out.println(inChangeablesBefore(WCUtil.getPersistableByOid("OR:wt.part.WTPart:176160"), getECAByNumber("00001")));
//		System.out.println(inChangeablesBefore(null, null));
	}
	
	public static void main(String[] args) throws RemoteException, InvocationTargetException, WTException {
		RemoteMethodServer r = RemoteMethodServer.getDefault();
		r.setUserName("wcadmin");
		r.setPassword("wcadmin");
		if (!RemoteMethodServer.ServerFlag) {
			RemoteMethodServer.getDefault().invoke("test", ChangeUtil.class.getName(), null, new Class[] {}, new Object[] {});
		}
	}
}
