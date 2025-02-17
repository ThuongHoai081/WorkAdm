package com.hnv.api.service.priv.tpy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hnv.api.def.DefAPI;
import com.hnv.api.def.DefJS;
import com.hnv.api.def.DefTime;
import com.hnv.api.interf.IService;
import com.hnv.api.main.API;
import com.hnv.api.service.common.APIAuth;
import com.hnv.api.service.common.ResultPagination;
import com.hnv.common.tool.ToolData;
import com.hnv.common.tool.ToolJSON;
import com.hnv.common.tool.ToolLogServer;
import com.hnv.common.util.CacheData;
import com.hnv.data.json.JSONObject;
import com.hnv.db.aut.TaAutUser;
import com.hnv.db.tpy.TaTpyFavorite;

/**
 * ----- ServiceTpyFavorite by H&V ----- Copyright 2017------------
 */
public class ServiceTpyFavorite implements IService {
	private static String       filePath                                 = null;
	private static String       urlPath                                  = null;

	public static final Integer NEW_CONTINUE                             = 1;
	public static final Integer NEW_EXIT                                 = 2;
	// --------------------------------Service
	// Definition----------------------------------
	public static final String  SV_MODULE                                = "EC_V3".toLowerCase();

	public static final String  SV_CLASS                                 = "ServiceTpyFavorite".toLowerCase();

	public static final String  SV_DO_NSO_FAVORITE_GET                   = "SVGet".toLowerCase();

	public static final String  SV_DO_NSO_FAVORITE_LST_BY_USER_ID        = "SVLstByUserId".toLowerCase();
	public static final String  SV_DO_NSO_FAVORITE_LST_BY_USER           = "SVLstByUser".toLowerCase();
	public static final String  SV_DO_NSO_FAVORITE_LST_BY_USER_ID_TYPE   = "SVLstByUserIdAndType".toLowerCase();
	public static final String  SV_DO_NSO_FAVORITE_COUNT_BY_USER_ID_TYPE = "SVCountByUserIdAndType".toLowerCase();
	// public static final String SV_DO_NSO_FAVORITE_LST_DYN =
	// "SVLstDyn".toLowerCase();

	// public static final String SV_DO_NSO_FAVORITE_LST_GRID =
	// "SVLstGrid".toLowerCase();
	// public static final String SV_DO_NSO_FAVORITE_LST_SEARCH =
	// "SVLstSearch".toLowerCase();
	// public static final String SV_DO_NSO_FAVORITE_LST_SEARCH_MANAGER=
	// "SVLstSearchManager".toLowerCase();
	// public static final String SV_DO_NSO_FAVORITE_LST_COUNT =
	// "SVLstCount".toLowerCase();
	//
	// public static final String SV_DO_NSO_FAVORITE_NEW = "SVNew".toLowerCase();
	public static final String SV_DO_NSO_FAVORITE_NSO_NEW = "SVNsoNew".toLowerCase();
	// public static final String SV_DO_NSO_FAVORITE_MOD = "SVMod".toLowerCase();
	public static final String SV_DO_NSO_FAVORITE_NSO_MOD = "SVNsoMod".toLowerCase();
	// public static final String SV_DO_NSO_FAVORITE_DEL = "SVDel".toLowerCase();
	//
	// public static final String SV_DO_NSO_FAVORITE_LCK_REQ =
	// "SVLckReq".toLowerCase(); //req or refresh
	// public static final String SV_DO_NSO_FAVORITE_LCK_SAV =
	// "SVLckSav".toLowerCase(); //save and continue
	// public static final String SV_DO_NSO_FAVORITE_LCK_END =
	// "SVLckEnd".toLowerCase();
	// public static final String SV_DO_NSO_FAVORITE_LCK_DEL =
	// "SVLckDel".toLowerCase();
	//
	// public static final String SV_DO_NSO_FAVORITE_LIST_DYN_SEARCH =
	// "SVLstDynSearch".toLowerCase();

	public static final String SV_DO_PRJ_PROJECT_NEW_FAVORITE    = "SVPrjProjectNewFavorite".toLowerCase();
	public static final String SV_DO_PRJ_PROJECT_REMOVE_FAVORITE = "SVPrjProjectRemoveFavorite".toLowerCase();

	// -----------------------------------------------------------------------------------------------
	// -------------------------Default Constructor - Required
	// -------------------------------------
	public ServiceTpyFavorite() {
		ToolLogServer.doLogInf("----" + SV_CLASS + " is loaded -----");
	}

	// -----------------------------------------------------------------------------------------------

	@Override
	public void doService(JSONObject json, HttpServletResponse response) throws Exception {
		// ToolLogServer.doLogInf("--------- "+ SV_CLASS+ ".doService --------------");
		String    sv   = API.reqSVFunctName(json);
		TaAutUser user = (TaAutUser) json.get("userInfo");
		try {
			// mapping service 

			if (sv.equals(SV_DO_NSO_FAVORITE_GET) && APIAuth.canAuthorize(user, SV_CLASS, sv)) {
				doGet(user, json, response);

			} else if (sv.equals(SV_DO_NSO_FAVORITE_LST_BY_USER_ID) && APIAuth.canAuthorize(user, SV_CLASS, sv)) {
				doLstByUserId(user, json, response);
			} else if (sv.equals(SV_DO_NSO_FAVORITE_LST_BY_USER) && APIAuth.canAuthorize(user, SV_CLASS, sv)) {
				doLstByUser(user, json, response);
			} else if (sv.equals(SV_DO_NSO_FAVORITE_LST_BY_USER_ID_TYPE) && APIAuth.canAuthorize(user, SV_CLASS, sv)) {
				doLstByUserIdAndType(user, json, response);
			} else if (sv.equals(SV_DO_NSO_FAVORITE_COUNT_BY_USER_ID_TYPE)
						&& APIAuth.canAuthorize(user, SV_CLASS, sv)) {
				doCountByUserIdAndType(user, json, response);
				// }
				// else if(sv.equals(SV_DO_NSO_FAVORITE_LST_DYN) && APIAuth.canAuthorize(user,
				// SV_CLASS, sv)){
				// doLstDyn(user, json, response);
				// }

				// else if(sv.equals(SV_DO_NSO_FAVORITE_LST_GRID) && APIAuth.canAuthorize(user,
				// SV_CLASS, sv)){
				// doLstGrid(user, json, response);
				// } else if(sv.equals(SV_DO_NSO_FAVORITE_LST_SEARCH) &&
				// APIAuth.canAuthorize(user, SV_CLASS, sv)){
				// doLstSearch(user, json, response);
				// } else if(sv.equals(SV_DO_NSO_FAVORITE_LST_SEARCH_MANAGER) &&
				// APIAuth.canAuthorize(user, SV_CLASS, sv)){
				// doLstSearchManager(user, json, response);
				//
				// } else if(sv.equals(SV_DO_NSO_FAVORITE_NEW) && APIAuth.canAuthorize(user,
				// SV_CLASS, sv)){
				// doNew(user, json, response);
			} else if (sv.equals(SV_DO_NSO_FAVORITE_NSO_NEW) && APIAuth.canAuthorize(user, SV_CLASS, sv)) {
				doNsoNew(user, json, response);
				// } else if(sv.equals(SV_DO_NSO_FAVORITE_MOD) && APIAuth.canAuthorize(user,
				// SV_CLASS, sv)){
				// doMod(user, json, response);
			} else if (sv.equals(SV_DO_NSO_FAVORITE_NSO_MOD) && APIAuth.canAuthorize(user, SV_CLASS, sv)) {
				doNsoMod(user, json, response);
				// } else if(sv.equals(SV_DO_NSO_FAVORITE_DEL) && APIAuth.canAuthorize(user,
				// SV_CLASS, sv)){
				// doDel(user, json, response);
				//
				// } else if(sv.equals(SV_DO_NSO_FAVORITE_LCK_REQ) && APIAuth.canAuthorize(user,
				// SV_CLASS, sv)){
				// doLckReq(user, json, response);
				// } else if(sv.equals(SV_DO_NSO_FAVORITE_LCK_SAV) && APIAuth.canAuthorize(user,
				// SV_CLASS, sv)){
				// doLckSav(user, json, response);
				// } else if(sv.equals(SV_DO_NSO_FAVORITE_LCK_END) && APIAuth.canAuthorize(user,
				// SV_CLASS, sv)){
				// doLckEnd(user, json, response);
				// } else if(sv.equals(SV_DO_NSO_FAVORITE_LCK_DEL) && APIAuth.canAuthorize(user,
				// SV_CLASS, sv)){
				// doLckDel(user, json, response);
				//
				// } else if(sv.equals(SV_DO_NSO_FAVORITE_LIST_DYN_SEARCH) &&
				// APIAuth.canAuthorize(user, SV_CLASS, sv)){
				// doLstDynSearch(user, json, response);

			} else if (sv.equals(SV_DO_PRJ_PROJECT_NEW_FAVORITE) && APIAuth.canAuthorize(user, SV_CLASS, sv)) {
				doPrjProjectNewFavorite(user, json, response);
			} else if (sv.equals(SV_DO_PRJ_PROJECT_REMOVE_FAVORITE) && APIAuth.canAuthorize(user, SV_CLASS, sv)) {
				doPrjProjectRemoveFavorite(user, json, response);

			} else {
				API.doResponse(response, DefAPI.API_MSG_ERR_RIGHT);
			}

		} catch (Exception e) {
			API.doResponse(response, DefAPI.API_MSG_ERR_API);
			return;
		}
	}
	// ---------------------------------------------------------------------------------------------------------

	private static final int WORK_GET = 1;
	private static final int WORK_LST = 2;
	private static final int WORK_NEW = 3;
	private static final int WORK_MOD = 4;
	private static final int WORK_DEL = 5;
	private static final int WORK_UPL = 10; // upload

	private static boolean canWorkWithObj(TaAutUser user, int typWork, Object... params) {
		switch (typWork) {
		case WORK_GET:
			// check something with params
			return true;
		case WORK_LST:
			// check something with params
			return true;
		case WORK_NEW:
			// check something with params
			return true;
		case WORK_MOD:
			// check something with params
			return true;
		case WORK_DEL:
			// check something with params
			return true;
		case WORK_UPL:
			// check something with params
			return true;
		}
		return false;
	}

	// ---------------------------------------------------------------------------------------------------------
	private static void doGet(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		// ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doGet --------------");

		Integer       objId      = ToolData.reqInt(json, "id", -1);
		Boolean       forced     = ToolData.reqBool(json, "forced", false);
		Boolean       forManager = ToolData.reqBool(json, "forManager", false);

		TaTpyFavorite ent        = reqGet(objId, forced, forManager);

		if (ent == null) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}
		API.doResponse(response,
				ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_YES, DefJS.RES_DATA, ent));
	}

	// ---------------------------------------------------------------------------------------------------------

	private static TaTpyFavorite reqGet(Integer objId, Boolean forced, boolean forManager) throws Exception {
		String        key = objId + "";
		TaTpyFavorite ent = cache_entity.reqData(key);
		if (forced || ent == null) {
			ent = TaTpyFavorite.DAO.reqEntityByRef(objId, forced);

			if (ent != null)
				cache_entity.reqPut(key, ent);
		} else {
			ToolLogServer.doLogInf("---reqGet use cache-----");
			cache_entity.reqCheckIfOld(key); // cache in 20 hour
		}

		// ---do build something other of ent like details....
		// if (ent!=null){
		// if(!forManager && (Integer)ent.req(TaTpyFavorite.ATT_I_STATUS) !=
		// TaTpyFavorite.FAVORITE_STATUS_VALIDATED) return null;
		// ent.doBuildAll(forced, forManager);
		// }

		return ent;
	}

	// ---------------------------------------------------------------------------------------------------------

	private static CacheData<TaTpyFavorite>       cache_entity = new CacheData<TaTpyFavorite>(500,
			DefTime.TIME_00_01_00_000);
	private static CacheData<ResultPagination>    cache_rs     = new CacheData<ResultPagination>(100,
			DefTime.TIME_00_20_00_000);
	private static CacheData<List<TaTpyFavorite>> cache_lst    = new CacheData<List<TaTpyFavorite>>(100,
			DefTime.TIME_00_00_05_000);
	private static CacheData<Map<Integer, TaTpyFavorite>> cache_map    = new CacheData<Map<Integer, TaTpyFavorite>>(100,
			DefTime.TIME_00_00_05_000);

	// ---------------------------------------------------------------------------------------------------------
	private static void doLstByUserId(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		// ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");

		List<TaTpyFavorite> lst = reqLstByUserId(user, json); // and other params if necessary
		if (lst == null || lst.size() == 0) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}
		API.doResponse(response,
				ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_YES, DefJS.RES_DATA, lst));
	}

	private static List<TaTpyFavorite> reqLstByUserId(TaAutUser user, JSONObject json, Object... params)
			throws Exception {
		Integer             userId  = user.reqId();
		Set<Integer>             entTyps  = ToolData.reqSetInt(json, "entTyps", null);

		String              keyWord = userId + "" + entTyps.toString();
		List<TaTpyFavorite> lst     = cache_lst.reqData(keyWord);

		if (lst == null || lst.size() <= 0) {
			ToolLogServer.doLogInf("---reqGetFavoriteByUserIdNew build list-----");

			Criterion cri = reqCriterion(userId);

			if (entTyps != null && entTyps.size() > 0) {
				cri = Restrictions.and(cri, Restrictions.in(TaTpyFavorite.ATT_I_ENTITY_TYPE, entTyps));
			}

			lst = TaTpyFavorite.DAO.reqList(cri);
			

			cache_lst.reqPut(keyWord, lst);
		} else {
			ToolLogServer.doLogInf("---reqGetFavoriteByUserIdNew use cache-----");
			cache_lst.reqCheckIfOld(keyWord); // cache in 2 hour
		}

		return lst;
	}

	private static void doLstByUser(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		// ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");

		List<TaTpyFavorite> lst = reqLstByUser(user, json); // and other params if necessary
		if (lst == null) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}
		API.doResponse(response,
				ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_YES, DefJS.RES_DATA, lst));
	}

	private static List<TaTpyFavorite> reqLstByUser(TaAutUser user, JSONObject json, Object... params)
			throws Exception {
		// other params here
		Criterion           cri = reqCriterion(user.reqId());

		List<TaTpyFavorite> lst = TaTpyFavorite.DAO.reqList(cri);

		return lst;
	}

	// ---------------------------------------------------------------------------------------------------------
	private static void doLstByUserIdAndType(TaAutUser user, JSONObject json, HttpServletResponse response)
			throws Exception {
		// ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");

		ResultPagination rs = reqLstByUserIdAndEntTyp(user, json); // and other params if necessary
		if (rs == null || rs.reqList().size() == 0) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}
		API.doResponse(response,
				ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_YES, DefJS.RES_DATA, rs));
	}

	private static ResultPagination reqLstByUserIdAndEntTyp(TaAutUser user, JSONObject json, Object... params)
			throws Exception {
		Boolean          pagination = ToolData.reqBool(json, "pagination", false);

		Integer          begin      = ToolData.reqInt(json, "begin", 0);
		Integer          number     = ToolData.reqInt(json, "number", 5);
		Integer          total      = ToolData.reqInt(json, "total", 0);
		Integer          entTyp     = ToolData.reqInt(json, "entTyp", 0);
		Boolean          forced     = ToolData.reqBool(json, "forced", false);

		Integer          userId     = (Integer) user.reqRef();
		String           keyWord    = userId + "_" + entTyp + "_" + begin + "_" + number + "_" + total;
		ResultPagination rs         = cache_rs.reqData(keyWord);

		if (rs == null) {
			ToolLogServer.doLogInf("---reqGetFavoriteByUserIdAndType build list-----");

			List<TaTpyFavorite> list = new ArrayList<TaTpyFavorite>();
			if (pagination)
				list = TaTpyFavorite.DAO.reqList(begin, number, Restrictions.eq(TaTpyFavorite.ATT_I_AUT_USER, userId),
						Restrictions.eq(TaTpyFavorite.ATT_I_ENTITY_TYPE, TaTpyFavorite.FAVORITE_TYPE_LIKE));

			else
				list = TaTpyFavorite.DAO.reqList(Restrictions.eq(TaTpyFavorite.ATT_I_AUT_USER, userId),
						Restrictions.eq(TaTpyFavorite.ATT_I_ENTITY_TYPE, TaTpyFavorite.FAVORITE_TYPE_LIKE));

			// AutTool.doBuildAutUsers (list, TaTpyFavorite.ATT_I_AUT_USER,
			// TaTpyFavorite.ATT_O_USER, forced);

			if (total == 0)
				total = (Integer) TaTpyFavorite.DAO.reqCount(Restrictions.eq(TaTpyFavorite.ATT_I_AUT_USER, userId),
						Restrictions.eq(TaTpyFavorite.ATT_I_ENTITY_TYPE, TaTpyFavorite.FAVORITE_TYPE_LIKE));

			rs = new ResultPagination(list, total, begin, number);
			cache_rs.reqPut(keyWord, rs);
		} else {
			ToolLogServer.doLogInf("---reqGetFavoriteByUserIdAndType use cache-----");
			cache_rs.reqCheckIfOld(keyWord); // cache in 2 hour
		}

		return rs;
	}

	// ---------------------------------------------------------------------------------------------------------
	private static void doCountByUserIdAndType(TaAutUser user, JSONObject json, HttpServletResponse response)
			throws Exception {
		// ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");

		ResultPagination rs = reqCountByUserIdAndEntTyp(user, json); // and other params if necessary
		if (rs == null) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		API.doResponse(response,
				ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_YES, DefJS.RES_DATA, rs));
	}

	private static int count_lim = 50;

	private static ResultPagination reqCountByUserIdAndEntTyp(TaAutUser user, JSONObject json, Object... params)
			throws Exception {

		Integer          total   = ToolData.reqInt(json, "total", 0);
		Integer          entTyp  = ToolData.reqInt(json, "entTyp", 0);
		Boolean          forced  = ToolData.reqBool(json, "forced", false);

		Integer          userId  = (Integer) user.reqRef();
		String           keyWord = userId + "_" + entTyp + "_" + total;
		ResultPagination rs      = cache_rs.reqData(keyWord);

		if (rs == null) {
			ToolLogServer.doLogInf("---reqGetFavoriteByUserIdAndType build list-----");

			if (total == 0)
				total = (Integer) TaTpyFavorite.DAO.reqCount(Restrictions.eq(TaTpyFavorite.ATT_I_AUT_USER, userId),
						Restrictions.eq(TaTpyFavorite.ATT_I_ENTITY_TYPE, TaTpyFavorite.FAVORITE_TYPE_LIKE));

			// ----check lim
			try {
				if (total > count_lim) {
					List<TaTpyFavorite> lsToDel = TaTpyFavorite.DAO.reqList(0, total - count_lim,
							Order.asc(TaTpyFavorite.ATT_I_ID),
							Restrictions.and(Restrictions.eq(TaTpyFavorite.ATT_I_AUT_USER, userId),
									Restrictions.eq(TaTpyFavorite.ATT_I_ENTITY_TYPE, entTyp),
									Restrictions.eq(TaTpyFavorite.ATT_I_TYPE, TaTpyFavorite.FAVORITE_TYPE_LIKE)));
					TaTpyFavorite.DAO.doRemove(lsToDel);
					total = count_lim;
				}
			} catch (Exception e) {
				ToolLogServer.doLogErr(e);
			}

			rs = new ResultPagination(null, total, null, null);

			cache_rs.reqPut(keyWord, rs);
		} else {
			ToolLogServer.doLogInf("---reqGetFavoriteByUserIdAndType use cache-----");
			cache_rs.reqCheckIfOld(keyWord); // cache in 2 hour
		}

		return rs;
	}

	// =========================================================================================================
	private static Criterion reqCriterion(Object... params) throws Exception {
		if (params == null || params.length == 0)
			return null;

		Criterion cri = Restrictions.gt("I_ID", 0);

		if (params != null && params.length > 0) {
			int userId = (int) params[0];
			cri = Restrictions.and(cri, Restrictions.eqOrIsNull(TaTpyFavorite.ATT_I_AUT_USER, userId));
		}

		if (params != null && params.length > 1) {
			// do something
		}

		return cri;
	}

	// =========================================================================================================
	private static Integer reqGetFavoriteLstByUserIdCount(Integer userId) throws Exception {
		Criterion cri   = Restrictions.eq(TaTpyFavorite.ATT_I_AUT_USER, userId);

		Integer   count = 0;
		count = TaTpyFavorite.DAO.reqCount(cri).intValue();

		return count;
	}

	// //---------------------------------------------------------------------------------------------------------
	//
	// private static Long countAllEle = null;
	// private static void doLstDynByUserId(TaAutUser user, JSONObject json,
	// HttpServletResponse response) throws Exception {
	// Object[] dataTableOption = reqDataTableOption (request);
	// List<String> searchKey = (List<String>)dataTableOption[0];
	// Integer stat = ToolData.reqInt(json, "stat");
	// Boolean forManager = ToolData.reqBool(json, "forManager" , false );
	//// Integer typ01 = ToolData.reqInt(json, "typ01");
	//// Integer typ02 = ToolData.reqInt(json, "typ02");
	//
	// if (!canWorkWithObj(user, WORK_LST, null)){ //other param after objTyp...
	// API.doResponse(response,JSONTool.reqJSonString(JSONDef.JS_OBJ_LOGGED, true,
	// JSONDef.JS_OBJ_SV_CODE, APICode.SV_CODE_API_NO));
	// return;
	// }
	//
	// Integer userId = user.reqUserId();
	// Integer userType = (Integer) user.req(TaAutUser.ATT_I_TYPE);
	// Criterion cri = null;
	// String sqlCommun = null;
	// List<TaTpyFavorite> list = new ArrayList<TaTpyFavorite>();
	//
	// if(userType == TaAutUser.TYPE_ADM || userType == TaAutUser.TYPE_ADM_ALL ||
	// !forManager){
	// cri = reqRestriction(searchKey, stat,null);
	// list = reqListDyn(dataTableOption, cri);
	// }
	// else{
	//// cri = reqRestriction(searchKey, stat,userId);
	// sqlCommun = DBConfig.TA_NSO_FAVORITE + " nsa INNER JOIN " +
	// DBConfig.TA_AUT_USER + " us "
	// + "ON nsa." + TaTpyFavorite.COL_I_AUT_USER + " = us." + TaAutUser.ATT_I_ID
	// +" WHERE nsa."+TaTpyFavorite.COL_I_AUT_USER +" in "
	// + "(SELECT tus." + TaAutUser.COL_I_ID+ " FROM " + DBConfig.TA_AUT_USER + "
	// tus WHERE "
	// + "tus."+TaAutUserInf.COL_I_SUPERVISOR + " = " + userId + " or tus." +
	// TaAutUser.COL_I_ID + "= " + userId + ")";
	// if(stat!=0 && stat!=null)
	// sqlCommun = sqlCommun + " AND nsa." + TaTpyFavorite.COL_I_STATUS + " = " +
	// stat;
	//
	// String sql = "SELECT nsa.* FROM " + sqlCommun;
	// list = reqListDynBySql(dataTableOption, sql);
	// }
	//
	// if (list==null ){
	// API.doResponse(response,JSONTool.reqJSonString(JSONDef.JS_OBJ_LOGGED, true,
	// JSONDef.JS_OBJ_SV_CODE, APICode.SV_CODE_API_NO));
	// return;
	// }
	//
	// if (countAllEle==null)
	// countAllEle = ((long)reqNbTpyFavoriteListDyn());
	//
	// Integer iTotalRecords = (countAllEle.intValue());
	// Integer iTotalDisplayRecords = null;
	//
	// if(userType == TaAutUser.TYPE_ADM || userType == TaAutUser.TYPE_ADM_ALL ||
	// !forManager){
	// iTotalDisplayRecords = reqNbTpyFavoriteListDyn(cri).intValue();
	// }else{
	// String sqlCount = "SELECT COUNT(*) FROM " + sqlCommun;
	// iTotalDisplayRecords = reqNbTpyFavoriteListDynBySql(sqlCount).intValue();
	// }
	//
	// API.doResponse(response,JSONTool.reqJSonString( //filter,
	// JSONDef.JS_OBJ_LOGGED , true,
	// JSONDef.JS_OBJ_SV_CODE , APICode.SV_CODE_API_YES,
	// "iTotalRecords" , iTotalRecords,
	// "iTotalDisplayRecords" , iTotalDisplayRecords,
	// "aaData" , list
	// ));
	//
	// }
	//
	// private static Object[] reqDataTableOption(JSONObject json){
	// /*{"sEcho":3,"iColumns":2,"sColumns":",",
	// "iDisplayStart":0, "iDisplayLength":10,
	// "mDataProp_0":"code","sSearch_0":"","bRegex_0":false,"bSearchable_0":true,"bSortable_0":true,
	// "mDataProp_1":"name","sSearch_1":"","bRegex_1":false,"bSearchable_1":true,"bSortable_1":true,
	// "sSearch":"f","bRegex":false,
	// "iSortCol_0":0,"sSortDir_0":"desc","iSortingCols":1}]
	// */
	//
	// Object[] res = new Object[5];
	//
	// String dataTableParam = request.getParameter("dataTableParam");
	// JSONObject dataTableParams = null;
	// if (dataTableParam!=null){
	// dataTableParams = JSONTool.reqJSonFromString(dataTableParam);
	// }
	//
	// List<String> keyword = new ArrayList<String>();
	// String searchKey = (String) dataTableParams.get("sSearch");
	// if (searchKey!=null && searchKey.length()>0){
	// StringTokenizer token = new StringTokenizer(searchKey, " ");
	// while (token.hasMoreTokens()){
	// String s = "%" +token.nextToken()+ "%";
	// s = s.replace("%$", "");
	// s = s.replace("$%", "");
	// keyword.add(s.toLowerCase());
	// }
	// }else{
	// keyword.add("%");
	// }
	//
	// long beginDisplay = (long)dataTableParams.get("iDisplayStart");
	// long nbDisplay = (long)dataTableParams.get("iDisplayLength");
	//
	// long colToSort = (long)dataTableParams.get("iSortCol_0");
	// long sortOption = 0;
	// String tmp = (String) dataTableParams.get("sSortDir_0");
	// if(tmp.equals("desc")) sortOption = 1;
	//
	// res[0] = keyword;
	// res[1] = beginDisplay;
	// res[2] = nbDisplay;
	// res[3] = colToSort;
	// res[4] = sortOption;
	// return res;
	// }
	//
	// private static Criterion reqRestriction(List<String> searchKey, Integer stat,
	// Integer userId) throws Exception {
	// Criterion cri = null;
	//
	// if(stat != 0){
	// cri = Restrictions.eq(TaTpyFavorite.ATT_I_STATUS, stat);
	// }
	// for (String s : searchKey){
	// if (cri==null)
	// cri = Restrictions.or(
	// Restrictions.ilike(TaTpyFavorite.ATT_T_REF , s),
	// Restrictions.ilike(TaTpyFavorite.ATT_T_TITLE , s));
	//
	// else
	// cri = Restrictions.and( cri,
	// Restrictions.or(
	// Restrictions.ilike(TaTpyFavorite.ATT_T_REF , s),
	// Restrictions.ilike(TaTpyFavorite.ATT_T_TITLE , s))
	// );
	// }
	//
	// if(userId != null)
	// cri = Restrictions.and( cri,
	// Restrictions.eq(TaTpyFavorite.ATT_I_AUT_USER, userId)
	// );
	//
	//// if(typ01 != null){
	//// cri = Restrictions.and(cri, Restrictions.eq(TaTpyFavorite.ATT_I_TYPE_01,
	// typ01));
	//// }
	//// if(typ02 != null){
	//// cri = Restrictions.and(cri, Restrictions.eq(TaTpyFavorite.ATT_I_TYPE_02,
	// typ02));
	//// }
	// return cri;
	// }
	//
	// private static List<TaTpyFavorite> reqListDyn(Object[] dataTableOption,
	// Criterion cri) throws Exception {
	// int begin = (int)(long) dataTableOption[1];
	// int number = (int)(long) dataTableOption[2];
	// int sortCol = (int)(long) dataTableOption[3];
	// int sortTyp = (int)(long) dataTableOption[4];
	//
	// List<TaTpyFavorite> list = new ArrayList<TaTpyFavorite>();
	//
	// Order order = null;
	// String colName = null;
	//
	// switch(sortCol){
	// case 0: colName = TaTpyFavorite.ATT_T_REF; break;
	// case 1: colName = TaTpyFavorite.ATT_T_TITLE; break;
	// }
	//
	// if (colName!=null){
	// switch(sortTyp){
	// case 0: order = Order.asc (colName); break;
	// case 1: order = Order.desc(colName); break;
	// }
	// }
	//
	// if (order==null)
	// list = TaTpyFavorite.DAO.reqList(begin, number, cri);
	// else
	// list = TaTpyFavorite.DAO.reqList(begin, number, order, cri);
	//
	// return list;
	// }
	//
	// private static List<TaTpyFavorite> reqListDynBySql(Object[] dataTableOption,
	// String sql) throws Exception {
	// int begin = (int)(long) dataTableOption[1];
	// int number = (int)(long) dataTableOption[2];
	// int sortCol = (int)(long) dataTableOption[3];
	// int sortTyp = (int)(long) dataTableOption[4];
	//
	// List<TaTpyFavorite> list = new ArrayList<TaTpyFavorite>();
	//
	// String colName = null;
	//
	// switch(sortCol){
	// case 0: colName = TaTpyFavorite.ATT_T_REF; break;
	// case 1: colName = TaTpyFavorite.ATT_T_TITLE; break;
	// }
	//
	// if (colName==null)
	// list = TaTpyFavorite.DAO.reqList(begin, number, sql);
	// else
	// sql = sql + " order by " + colName;
	// list = TaTpyFavorite.DAO.reqList(begin, number, sql);
	//
	// return list;
	// }
	//
	// private static Number reqNbTpyFavoriteListDyn() throws Exception {
	// return TaTpyFavorite.DAO.reqCount();
	// }
	//
	// private static Number reqNbTpyFavoriteListDyn(Criterion cri) throws Exception
	// {
	// return TaTpyFavorite.DAO.reqCount(cri);
	// }
	//
	// private static Number reqNbTpyFavoriteListDynBySql(String sql) throws
	// Exception {
	// return TaTpyFavorite.DAO.reqCount(sql);
	// }
	//
	// //---------------------------------------------------------------------------------------------------------
	// //--cache for UI public
	// private static CacheData<TaTpyFavorite> cache_entity= new
	// CacheData<TaTpyFavorite> (500, DefTime.TIME_20_00_00_000);
	// private static CacheData<ResultPagination> cache_rs = new
	// CacheData<ResultPagination> (100, DefTime.TIME_02_00_00_000);
	// //--cache for UI Manager
	// private static CacheData<List<TaTpyFavorite>> cache_manager = new
	// CacheData<List<TaTpyFavorite>>();
	// //---------------------------------------------------------------------------------------------------------
	// private static void doLstGrid(TaAutUser user, JSONObject json,
	// HttpServletResponse response) throws Exception {
	// //ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");
	//
	// ResultPagination res = reqLstGrid(user, json); //and other params if
	// necessary
	// if (res.reqList()==null || res.reqList().size()==0){
	// API.doResponse(response,JSONTool.reqJSonString(JSONDef.JS_OBJ_LOGGED, true,
	// JSONDef.JS_OBJ_SV_CODE, APICode.SV_CODE_API_NO));
	// return;
	// }
	//
	// API.doResponse(response,JSONTool.reqJSonString( //filter,
	// JSONDef.JS_OBJ_LOGGED , true,
	// JSONDef.JS_OBJ_SV_CODE , APICode.SV_CODE_API_YES,
	// JSONDef.JS_OBJ_RES_DATA , res
	// ));
	// }
	//
	// private static ResultPagination reqLstGrid(TaAutUser user, JSONObject json,
	// Object...params) throws Exception {
	//// Integer objMan = ToolData.reqInt(json, "manId" , null );
	//// Boolean objContBuild= ToolData.reqBool(json, "withBuild" , false );
	//
	// Integer objTyp = ToolData.reqInt(json, "typ" , null );
	// Integer style = ToolData.reqInt(json, "style" , null );
	// Integer begin = ToolData.reqInt(json, "begin" , 0 );
	// Integer number = ToolData.reqInt(json, "number" , 20);
	// Integer total = ToolData.reqInt(json, "total" , 0 );
	//
	// //other params here
	//
	// if (!canWorkWithObj(user, WORK_LST, objTyp)){ //other param after objTyp...
	// return null;
	// }
	//
	// String keyWord = objTyp + "_" + TaTpyFavorite.FAVORITE_STATUS_VALIDATED + "_"
	// + style +"_" + begin + "_" + number + "_" + total;
	// ResultPagination rs = cache_rs.reqData(keyWord);
	//
	// if(rs==null) {
	// ToolLogServer.doLogInf("---reqLstGrid build vi-----");
	// List<TaTpyFavorite> list = TaTpyFavorite.reqLstGrid(begin, number, objTyp,
	// TaTpyFavorite.FAVORITE_STATUS_VALIDATED, style);
	// if (total == 0 ) total = (Integer)TaTpyFavorite.reqLstGridCount(objTyp,
	// TaTpyFavorite.FAVORITE_STATUS_VALIDATED, style);
	// rs = new ResultPagination(list, total, begin, number);
	// cache_rs.reqPut(keyWord, rs);
	// } else {
	// ToolLogServer.doLogInf("---reqLstGrid use cache-----");
	// cache_rs.reqCheckIfOld(keyWord); //cache in 2 hour
	// }
	//
	// return rs;
	// }
	//
	// //---------------------------------------------------------------------------------------------------------
	// private static void doLstSearch(TaAutUser user, JSONObject json,
	// HttpServletResponse response) throws Exception {
	// //ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");
	//
	// ResultPagination res = reqLstSearch(user, json); //and other params if
	// necessary
	// if (res.reqList()==null || res.reqList().size()==0){
	// API.doResponse(response,JSONTool.reqJSonString(JSONDef.JS_OBJ_LOGGED, true,
	// JSONDef.JS_OBJ_SV_CODE, APICode.SV_CODE_API_NO));
	// return;
	// }
	//
	// API.doResponse(response,JSONTool.reqJSonString( //filter,
	// JSONDef.JS_OBJ_LOGGED , true,
	// JSONDef.JS_OBJ_SV_CODE , APICode.SV_CODE_API_YES,
	// JSONDef.JS_OBJ_RES_DATA , res
	// ));
	// }
	//
	// private static ResultPagination reqLstSearch(TaAutUser user, JSONObject json,
	// Object...params) throws Exception {
	// Integer begin = ToolData.reqInt(json, "begin" , 0 );
	// Integer number = ToolData.reqInt(json, "number" , 20);
	// Integer total = ToolData.reqInt(json, "total" , 0 );
	// JSONObject searchFilter = API.reqParamJson (request, "filter");//array
	// //--typCat : [array int ]
	// //--searchKey : sKey
	// //--latitude : lat
	// //--longitude : long
	//
	//// String searchKey = searchFilter.get("searchKey"
	// ).toString().isEmpty()?"%":searchFilter.get("searchKey").toString();
	//// String lstCat = searchFilter.get("typCat"
	// ).toString().isEmpty()?"0":searchFilter.get("typCat").toString();
	//// Double pDist = 10.0;
	// //Double.parseDouble(searchFilter.get("distance").toString());
	//// Double pLat = Double.parseDouble(searchFilter.get("latitude"
	// ).toString().isEmpty()?"0":searchFilter.get("latitude").toString());
	//// Double pLong = Double.parseDouble(searchFilter.get("longitude"
	// ).toString().isEmpty()?"0":searchFilter.get("longitude").toString());
	//
	// String searchKey = searchFilter.get("sKey")
	// !=null?searchFilter.get("sKey").toString() :"%";
	// String lstCat = searchFilter.get("typ")
	// !=null?searchFilter.get("typ").toString() :"0";
	// Double pDist = 10.0; //Double.parseDouble(searchFilter.get("dis" )
	// !=null?searchFilter.get("dis").toString() :"0");
	// Double pLat = Double.parseDouble(searchFilter.get("lat" )
	// !=null?searchFilter.get("lat").toString() :"0");
	// Double pLong = Double.parseDouble(searchFilter.get("lng")
	// !=null?searchFilter.get("lng").toString() :"0");
	//
	//
	// List<GeoPoint> lstPoint = new ArrayList<GeoPoint>();
	// if(!pLat.equals(0.0) && !pLong.equals(0.0))
	// lstPoint = GeoPoint.reqBound(pLat, pLong, pDist); //5 diem, diem dau tien
	// origin, Nord, south, est, west
	//
	// String keyWord = searchKey + "_" + lstCat + "_" + pLat +"_" + pLong + "_" +
	// begin + "_" + number + "_" + total;
	// ResultPagination rs = cache_rs.reqData(keyWord);
	//
	// if(rs==null) {
	// ToolLogServer.doLogInf("---reqLstSearch build vi-----");
	// List<TaTpyFavorite> list = TaTpyFavorite.reqLstSearch(begin, number,
	// TaTpyFavorite.FAVORITE_STATUS_VALIDATED, searchKey, lstCat, lstPoint);
	// if (total == 0 ) total =
	// (Integer)TaTpyFavorite.reqLstSearchCount(TaTpyFavorite.FAVORITE_STATUS_VALIDATED,
	// searchKey, lstCat, lstPoint);
	// rs = new ResultPagination(list, total, begin, number);
	// cache_rs.reqPut(keyWord, rs);
	// } else {
	// ToolLogServer.doLogInf("---reqLstSearch user cache-----");
	// cache_rs.reqCheckIfOld(keyWord); //cache in 2 hour
	// }
	//
	// return rs;
	// }
	//
	// //---------------------------------------------------------------------------------------------------------
	// private static void doLstSearchManager(TaAutUser user, JSONObject json,
	// HttpServletResponse response) throws Exception {
	// //ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLst --------------");
	// Integer nbLine = API.reqParamInt (request, "nbLine" , 20);
	// String searchKey = API.reqParamString (request, "searchKey","");
	// Integer userId = (Integer) user.reqRef();
	//
	// List<TaTpyFavorite> res = cache_manager.reqData(searchKey);
	//
	// if(res == null) {
	// res = TaTpyFavorite.reqListFavorite(null, searchKey.trim(),
	// TaTpyFavorite.FAVORITE_STATUS_VALIDATED, nbLine); //and other params if
	// necessary
	// cache_manager.doPut(searchKey, res);
	// }else {
	// ToolLogServer.doLogInf("---reqLstSearchManage user cache-----");
	// cache_manager.doCheckIfOld(searchKey, DefTime.TIME_02_00_00_000); //cache in
	// 2 hour
	// }
	//
	// if (res==null || res.size()==0){
	// API.doResponse(response,JSONTool.reqJSonString(JSONDef.JS_OBJ_LOGGED, true,
	// JSONDef.JS_OBJ_SV_CODE, APICode.SV_CODE_API_NO));
	// return;
	// }
	//
	// API.doResponse(response,JSONTool.reqJSonString( //filter,
	// JSONDef.JS_OBJ_LOGGED , true,
	// JSONDef.JS_OBJ_SV_CODE , APICode.SV_CODE_API_YES,
	// JSONDef.JS_OBJ_RES_DATA , res
	// ));
	// }
	//
	// ---------------------------------------------------------------------------------------------------------

	// private static void doNew(TaAutUser user, JSONObject json,
	// HttpServletResponse response) throws Exception {
	// //ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doNew --------------");
	// //--- in simple case, obj has only header , no details ----------------------
	// //Map<String, Object> attr = API.reqMapParamsByClass(request,
	// TaTpyFavorite.class);
	// //TaTpyFavorite ent = new TaTpyFavorite(attr);
	// //TaTpyFavorite.DAO.doPersist(ent);
	// //----------------------------------------------------------------------------------------------------------------------
	//
	// TaTpyFavorite ent = reqNew (user, json);
	// if (ent==null){
	// API.doResponse(response,JSONTool.reqJSonString(
	// JSONDef.JS_OBJ_LOGGED , true,
	// JSONDef.JS_OBJ_SV_CODE , APICode.SV_CODE_API_NO
	// ));
	// return;
	// }
	//
	// Integer wLock = ToolData.reqInt(json, "lock");
	// if(wLock == NEW_CONTINUE){
	// LockInterface lock = LockTool.reqLock(DBConfig.ID_TA_, (Integer)ent.reqRef(),
	// user.reqUserId());
	//
	// API.doResponse(response,JSONTool.reqJSonString(
	// JSONDef.JS_OBJ_LOGGED , true,
	// JSONDef.JS_OBJ_SV_CODE , APICode.SV_CODE_API_YES,
	// JSONDef.JS_OBJ_RES_DATA , ent,
	// "lock" , lock
	// ));
	// }else{
	// API.doResponse(response,JSONTool.reqJSonString(
	// JSONDef.JS_OBJ_LOGGED , true,
	// JSONDef.JS_OBJ_SV_CODE , APICode.SV_CODE_API_YES,
	// JSONDef.JS_OBJ_RES_DATA , ent
	// ));
	// }
	//
	// }

	// private static TaTpyFavorite reqNew(TaAutUser user, JSONObject json) throws
	// Exception {
	// JSONObject obj = API.reqParamJson (request, "obj");
	// Map<String, Object> attr = API.reqMapParamsByClass(obj, TaTpyFavorite.class);
	// attr.put(TaTpyFavorite.ATT_D_DATE_ADD, new Date());
	// attr.put(TaTpyFavorite.ATT_D_DATE_MOD, new Date());
	// TaTpyFavorite ent = new TaTpyFavorite(attr);
	//
	// if (!canWorkWithObj(user, WORK_NEW, ent)){ //other param after obj...
	// return null;
	// }
	//
	// //check ref not existed
	// List<TaTpyFavorite> lst =
	// TaTpyFavorite.DAO.reqList(Restrictions.eq(TaTpyFavorite.ATT_T_REF,
	// ent.req(TaTpyFavorite.ATT_T_REF)));
	// if(lst.size() > 0) return null;
	//
	// TaTpyFavorite.DAO.doPersist(ent);
	//
	// reqSaveFavoriteDetail(user, ent, obj);
	//
	// int objId = (int) ent.req(TaTpyFavorite.ATT_I_ID);
	//
	// JSONArray lstOffers = (JSONArray) obj.get("offers");
	// List<JSONObject> relstOfferNew = new ArrayList<>();
	// if (lstOffers != null && lstOffers.size() >= 0) {
	// for(int i=0; i<lstOffers.size(); i++) {
	// JSONObject offer = (JSONObject) lstOffers.get(i);
	// relstOfferNew.add(offer);
	// }
	// }
	// if(relstOfferNew.size()>0) {
	// reqNewFavoriteOffer(objId, relstOfferNew);
	// ent.doBuildOffers(true);
	// }
	//
	// JSONArray docs = (JSONArray) obj.get("files");
	// //Update attach files
	// if(docs.size() > 0) {
	// List<TaTpyDocument> lstDoc =
	// DocTool.reqTpyDocumentNew(DBConfig.ID_TA_NSO_FAVORITE, objId, docs);
	// ServiceTpyDocument.doSetPriorityFiles(lstDoc, docs);
	// ent.reqSet(TaTpyFavorite.ATT_O_DOCUMENTS, lstDoc);
	// }
	//
	// //build other objects from obj and request
	//
	// return ent;
	// }

	// ---------------------------------------------------------------------------------------------------------
	private static void doNsoNew(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		TaTpyFavorite ent = null;
		ent = reqNsoNew(user, json);

		if (ent == null) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}
		API.doResponse(response,
				ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_YES, DefJS.RES_DATA, ent));

	}

	private static TaTpyFavorite reqNsoNew(TaAutUser user, JSONObject json) throws Exception {
		JSONObject          obj  = ToolData.reqJson(json, "obj", null);
		Map<String, Object> attr = API.reqMapParamsByClass(obj, TaTpyFavorite.class);

		TaTpyFavorite       ent  = new TaTpyFavorite(attr);

		if (!canWorkWithObj(user, WORK_NEW, ent)) { // other param after obj...
			return null;
		}

		ent.reqSet(TaTpyFavorite.ATT_I_AUT_USER, user.reqId());
		ent.reqSet(TaTpyFavorite.ATT_I_TYPE, TaTpyFavorite.FAVORITE_TYPE_LIKE);

		// check exist favorite
		List<TaTpyFavorite> lst = TaTpyFavorite.DAO.reqList(
				Restrictions.eq(TaTpyFavorite.ATT_I_AUT_USER, ent.req(TaTpyFavorite.ATT_I_AUT_USER)),
				Restrictions.eq(TaTpyFavorite.ATT_I_ENTITY_TYPE, ent.req(TaTpyFavorite.ATT_I_ENTITY_TYPE)),
				Restrictions.eq(TaTpyFavorite.ATT_I_ENTITY_ID, ent.req(TaTpyFavorite.ATT_I_ENTITY_ID)));
		if (lst.size() > 0)
			return null;

		TaTpyFavorite.DAO.doPersist(ent);
		return ent;
	}

	// ---------------------------------------------------------------------------------------------------------
	private static void doNsoMod(TaAutUser user, JSONObject json, HttpServletResponse response) throws Exception {
		// ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doMod --------------");

		TaTpyFavorite ent = reqNsoMod(user, json);
		if (ent == null) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}
		API.doResponse(response,
				ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_YES, DefJS.RES_DATA, ent));
	}

	private static TaTpyFavorite reqNsoMod(TaAutUser user, JSONObject json) throws Exception {
		Integer             entTyp = ToolData.reqInt	(json, "entTyp", 	null);
		String        		data   = ToolData.reqStr	(json, "data", 		null);

		Criterion           cri    = 	Restrictions.eq(TaTpyFavorite.ATT_I_AUT_USER, 		user.reqId());
		cri = Restrictions.and(cri, 	Restrictions.eq(TaTpyFavorite.ATT_I_ENTITY_TYPE, 	entTyp));

		TaTpyFavorite       ent = null;
		List<TaTpyFavorite> lst = TaTpyFavorite.DAO.reqList(cri);
		if (lst.size() > 0)
			ent = lst.get(0);

		if (ent == null) {
			ent  = new TaTpyFavorite();
			ent.reqSet(TaTpyFavorite.ATT_I_AUT_USER, 	user.reqId());
			ent.reqSet(TaTpyFavorite.ATT_I_ENTITY_TYPE, entTyp);
			TaTpyFavorite.DAO.doPersist(ent);
		}
		
		ent.reqSet(TaTpyFavorite.ATT_T_DESRCIPTION, data);
		TaTpyFavorite.DAO.doMerge(ent);

		return ent;
	}

	// private static String reqSaveFavoriteDetail(TaAutUser user, TaTpyFavorite
	// area, JSONObject obj) throws Exception {
	//
	// int objId = (int) area.req(TaTpyFavorite.ATT_I_ID);
	// int idUser = (int) user.reqRef();
	// // --merge other objects from obj and request
	//
	// JSONArray lstDetail = (JSONArray) obj.get("details");
	// if (lstDetail != null && lstDetail.size() >= 0) {
	// List<TaTpyFavoriteDetail> details =
	// ServiceTpyFavoriteDetail.reqDetailMod(objId, lstDetail);
	// if (details == null)
	// return null;
	// area.reqSet(TaTpyFavorite.ATT_O_DETAILS, details);
	//
	// }
	//
	//
	// // Save area to database
	// TaTpyFavorite.DAO.doMerge(area);
	//
	// return null;
	// }
	//
	// //---------------------------------------------------------------------------------------------------------
	// private static void doMod(TaAutUser user, JSONObject json,
	// HttpServletResponse response) throws Exception {
	// //ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doMod --------------");
	//
	// TaTpyFavorite ent = reqMod(user, json);
	// if (ent==null){
	// API.doResponse(response,JSONTool.reqJSonString(JSONDef.JS_OBJ_LOGGED, true,
	// JSONDef.JS_OBJ_SV_CODE, APICode.SV_CODE_API_NO));
	// } else {
	// API.doResponse(response,JSONTool.reqJSonString(
	// JSONDef.JS_OBJ_LOGGED , true,
	// JSONDef.JS_OBJ_SV_CODE , APICode.SV_CODE_API_YES,
	// JSONDef.JS_OBJ_RES_DATA , ent
	// ));
	// }
	// }
	//
	// private static TaTpyFavorite reqMod(TaAutUser user, JSONObject json) throws
	// Exception {
	// JSONObject obj = ToolData.reqJson(json, "obj");
	//
	// Map<String, Object> attr = API.reqMapParamsByClass(obj, TaTpyFavorite.class);
	//
	// int objId = Integer.parseInt((String) obj.get("id"));
	// TaTpyFavorite ent = TaTpyFavorite.DAO.reqEntityByRef(objId);
	// if (ent==null){
	// return null;
	// }
	//
	// if (!canWorkWithObj(user, WORK_MOD, ent)){ //other param after obj...
	// return null;
	// }
	//
	// //check ref not existed
	// String oldRef = (String) ent.req(TaTpyFavorite.ATT_T_REF);
	// String newRef = (String) attr.get(TaTpyFavorite.ATT_T_REF);
	//
	// if(!oldRef.equals(newRef)) {
	// List<TaTpyFavorite> lst =
	// TaTpyFavorite.DAO.reqList(Restrictions.eq(TaTpyFavorite.ATT_T_REF, newRef));
	// if(lst.size() > 0) return null;
	// }
	//
	// attr.remove(TaTpyFavorite.ATT_D_DATE_ADD);
	// attr.put(TaTpyFavorite.ATT_D_DATE_MOD, new Date());
	//
	// TaTpyFavorite.DAO.doMerge(ent, attr);
	//
	// reqSaveFavoriteDetail(user,ent,obj);
	//
	// reqSaveFavoriteOffer(user,ent,obj);
	// //merge other objects from obj and request
	//
	// JSONArray docs = (JSONArray) obj.get("files");
	//
	// List<TaTpyDocument> lstDoc = DocTool.reqTpyDocumentMod(
	// DBConfig.ID_TA_NSO_FAVORITE, objId, docs);
	//
	// ServiceTpyDocument.doSetPriorityFiles(lstDoc, docs);
	//
	// ent.reqSet(TaTpyFavorite.ATT_O_DOCUMENTS, lstDoc);
	//
	// return ent;
	// }
	//
	// private static void reqSaveFavoriteOffer(TaAutUser user, TaTpyFavorite area,
	// JSONObject obj) throws Exception{
	// int objId = (int) area.req(TaTpyFavorite.ATT_I_ID);
	// int idUser = (int) user.reqRef();
	// // --merge other objects from obj and request
	//
	// JSONArray lstOffers = (JSONArray) obj.get("offers");
	//
	// List<TaTpyFavoriteOfferFavorite> reLst =
	// TaTpyFavoriteOfferFavorite.DAO.reqList(Restrictions.eq(TaTpyFavoriteOfferFavorite.ATT_I_FAVORITE,
	// objId));
	//
	// Collection<TaTpyFavoriteOfferPlan> relstDel = new
	// ArrayList<TaTpyFavoriteOfferPlan>();
	//
	// List<JSONObject> relstOfferNew = new ArrayList<>();
	//
	// HashMap<Integer, TaTpyFavoriteOfferPlan> mapOffers = new HashMap <Integer,
	// TaTpyFavoriteOfferPlan>();
	//
	// if(reLst != null && reLst.size() > 0) {
	// for(int i=0; i<reLst.size(); i++) {
	// TaTpyFavoriteOfferPlan re = reLst.get(i);
	// mapOffers.put((Integer) re.req(TaTpyFavoriteOfferPlan.ATT_I_OFFER), re);
	// }
	// }
	// if (lstOffers != null && lstOffers.size() >= 0) {
	// for(int i=0; i<lstOffers.size(); i++) {
	// JSONObject offer = (JSONObject) lstOffers.get(i);
	// int offerId = ((Long) offer.get("id")).intValue();
	// if(mapOffers.containsKey(offerId)) {
	// mapOffers.remove(offerId);
	// }else {
	// relstOfferNew.add(offer);
	// }
	// }
	// }
	// if(mapOffers.size()>0) {
	// relstDel = mapOffers.values();
	// }
	//
	// reqNewFavoriteOffer(objId, relstOfferNew);
	// reqDelFavoriteOffer(relstDel);
	// if(relstOfferNew.size()>0 || relstDel.size()>0)
	// area.doBuildOffers(true);
	// }
	//
	// private static void reqNewFavoriteOffer(Integer areaId, List<JSONObject>
	// lstOffer) throws Exception{
	// if(lstOffer != null && lstOffer.size()>0) {
	// for(int i = 0; i<lstOffer.size(); i++) {
	// JSONObject offer = (JSONObject) lstOffer.get(i);
	// int offerId = ((Long) offer.get("id")).intValue();
	// TaTpyFavoriteOfferPlan areaOffer = new TaTpyFavoriteOfferPlan(1, 1, null,
	// offerId, areaId, null, new Date());
	// TaTpyFavoriteOfferPlan.DAO.doPersist(areaOffer);
	// }
	// }
	// }
	//
	// private static void reqDelFavoriteOffer(Collection<TaTpyFavoriteOfferPlan>
	// relstDel) throws Exception{
	// if(relstDel != null && relstDel.size()>0) {
	// TaTpyFavoriteOfferPlan.DAO.doRemove(relstDel);
	// }
	// }
	//
	//
	// //---------------------------------------------------------------------------------------------------------
	// private static void doDel(TaAutUser user, JSONObject json,
	// HttpServletResponse response) throws Exception {
	// //ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doDel --------------");
	//
	// //--chk
	// lock-----------------------------------------------------------------------------------------------------------------
	// JSONObject jLock = ToolData.reqJson(json, "lock");
	// LockInterface lock = LockTool.reqLock(jLock, user.reqUserId());
	//
	// if (lock.reqStatus() == 0){
	// API.doResponse(response,JSONTool.reqJSonString(
	// JSONDef.JS_OBJ_LOGGED , true,
	// JSONDef.JS_OBJ_SV_CODE , APICode.SV_CODE_API_NO,
	// JSONDef.JS_OBJ_RES_DATA , lock
	// ));
	// return;
	// }
	//
	// //----------------------------------------------------------------------------------------------------------------------
	// boolean ok = canTpyFavoriteDel(user, json);
	// if (!ok){
	// API.doResponse(response,JSONTool.reqJSonString(JSONDef.JS_OBJ_LOGGED, true,
	// JSONDef.JS_OBJ_SV_CODE, APICode.SV_CODE_API_NO));
	// } else {
	// API.doResponse(response,JSONTool.reqJSonString(JSONDef.JS_OBJ_LOGGED, true,
	// JSONDef.JS_OBJ_SV_CODE, APICode.SV_CODE_API_YES));
	// }
	//
	// //--del
	// lock-----------------------------------------------------------------------------------------------------------------
	// LockTool.canDeleteLock(lock.reqLockID(), user.reqUserId());
	// }
	// private static boolean canTpyFavoriteDel(TaAutUser user, JSONObject json)
	// throws Exception {
	// Integer objId = ToolData.reqInt(json, "id" , -1 );
	//
	// TaTpyFavorite ent = TaTpyFavorite.DAO.reqEntityByRef(objId);
	// if (ent==null){
	// return false;
	// }
	//
	// if (!canWorkWithObj(user, WORK_DEL, ent)){ //other param after ent...
	// return false;
	// }
	//
	// //remove all other object connecting to this ent first-------
	// Integer areaId = (Integer) ent.req(TaTpyFavorite.ATT_I_ID);
	//
	// ServiceTpyFavoriteDetail.doDetailDel (areaId);
	//
	// DocTool.doTpyDocumentDel (DBConfig.ID_TA_NSO_FAVORITE, areaId);
	//
	// TaTpyFavorite.DAO.doRemove(ent);
	// return true;
	// }
	//
	// //---------------------------------------------------------------------------------------------------------
	// //---------------------------------------------------------------------------------------------------------
	// //---------------------------------------------------------------------------------------------------------
	// private void doLckReq(TaAutUser user, JSONObject json, HttpServletResponse
	// response) throws Exception {
	// //ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLckReq
	// --------------");
	//
	// JSONObject obj = ToolData.reqJson(json, JSONDef.JS_OBJ_REQ_DATA);
	//
	// LockInterface lock = LockTool.reqLock(obj, user.reqUserId());
	// if (lock.reqStatus() == 0){
	// API.doResponse(response,JSONTool.reqJSonString(
	// JSONDef.JS_OBJ_LOGGED , true,
	// JSONDef.JS_OBJ_SV_CODE , APICode.SV_CODE_API_NO,
	// JSONDef.JS_OBJ_RES_DATA , lock
	// ));
	// }else{
	// /* check right on type of object if ....
	// /*
	// int objId = (int) obj.get(JSONDef.JS_OBJ_LOCK_OBJ_ID);
	// TaTpyFavorite ent = TaTpyFavorite.DAO.reqEntityByRef(objId);
	// if (!canWorkWithObj(user, WORK_MOD, ent)){ //other param after ent...
	// API.doResponse(response,JSONTool.reqJSonString(JSONDef.JS_OBJ_LOGGED, true,
	// JSONDef.JS_OBJ_SV_CODE, APICode.SV_CODE_API_NO));
	// return;
	// }
	// */
	//
	// API.doResponse(response,JSONTool.reqJSonString(
	// JSONDef.JS_OBJ_LOGGED , true,
	// JSONDef.JS_OBJ_SV_CODE , APICode.SV_CODE_API_YES,
	// JSONDef.JS_OBJ_RES_DATA , lock
	// ));
	// }
	// }
	//
	// private void doLckSav(TaAutUser user, JSONObject json, HttpServletResponse
	// response) throws Exception {
	// //ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLckSav
	// --------------");
	//
	// int idLock = Integer.parseInt(request.getParameter(JSONDef.JS_OBJ_LOCK_ID));
	// boolean isLocked = LockTool.canExistLock(idLock , user.reqUserId());
	// if(isLocked){
	// TaTpyFavorite ent = reqMod(user, json);
	// if (ent==null){
	// API.doResponse(response,JSONTool.reqJSonString(JSONDef.JS_OBJ_LOGGED, true,
	// JSONDef.JS_OBJ_SV_CODE, APICode.SV_CODE_API_NO));
	// } else {
	// API.doResponse(response,JSONTool.reqJSonString(
	// JSONDef.JS_OBJ_LOGGED , true,
	// JSONDef.JS_OBJ_SV_CODE , APICode.SV_CODE_API_YES,
	// JSONDef.JS_OBJ_RES_DATA , ent
	// ));
	// }
	// }else{
	// API.doResponse(response,JSONTool.reqJSonString(
	// JSONDef.JS_OBJ_LOGGED , true,
	// JSONDef.JS_OBJ_SV_CODE , APICode.SV_CODE_API_NO
	// ));
	// }
	// }
	//
	//
	// //user when modify object with lock
	// private void doLckEnd(TaAutUser user, JSONObject json, HttpServletResponse
	// response) throws Exception {
	// //ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLckEnd
	// --------------");
	//
	// int idLock = Integer.parseInt(request.getParameter(JSONDef.JS_OBJ_LOCK_ID));
	// boolean isLocked = LockTool.canExistLock(idLock , user.reqUserId());
	// if(isLocked){
	// TaTpyFavorite ent = reqMod(user, json);
	// if (ent==null){
	// API.doResponse(response,JSONTool.reqJSonString(JSONDef.JS_OBJ_LOGGED, true,
	// JSONDef.JS_OBJ_SV_CODE, APICode.SV_CODE_API_NO));
	// } else {
	// LockTool.canDeleteLock(idLock, user.reqUserId());
	// API.doResponse(response,JSONTool.reqJSonString(
	// JSONDef.JS_OBJ_LOGGED , true,
	// JSONDef.JS_OBJ_SV_CODE , APICode.SV_CODE_API_YES,
	// JSONDef.JS_OBJ_RES_DATA , ent
	// ));
	// }
	// }else{
	// API.doResponse(response,JSONTool.reqJSonString(
	// JSONDef.JS_OBJ_LOGGED , true,
	// JSONDef.JS_OBJ_SV_CODE , APICode.SV_CODE_API_NO
	// ));
	// }
	// }
	//
	// private void doLckDel(TaAutUser user, JSONObject json, HttpServletResponse
	// response) throws Exception {
	// //ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doLckDel
	// --------------");
	//
	// Integer lockId = ToolData.reqInt(json, JSONDef.JS_OBJ_LOCK_ID , -1);
	// boolean isDeleted = LockTool.canDeleteLock(lockId, user.reqUserId());
	// API.doResponse(response,JSONTool.reqJSonString(
	// JSONDef.JS_OBJ_LOGGED , true,
	// JSONDef.JS_OBJ_SV_CODE , APICode.SV_CODE_API_YES,
	// JSONDef.JS_OBJ_RES_DATA , isDeleted
	// ));
	//
	// }
	//
	// //-------------------REQUEST LIST FAVORITE
	// SEARCH--------------------------------------------------
	// private static void doLstDynSearch(TaAutUser user, JSONObject json,
	// HttpServletResponse response) throws Exception {
	// Integer stat = ToolData.reqInt(json, "stat");
	// String searchkey = API.reqParamString(request, "searchkey" , "%");
	// Integer nbLineMax = ToolData.reqInt(json, "nbLine" , 10);
	//
	// if (!canWorkWithObj(user, WORK_LST, null)){ //other param after objTyp...
	// API.doResponse(response,JSONTool.reqJSonString(JSONDef.JS_OBJ_LOGGED, true,
	// JSONDef.JS_OBJ_SV_CODE, APICode.SV_CODE_API_NO));
	// return;
	// }
	//
	//
	// Criterion cri = Restrictions.like(TaTpyFavorite.ATT_T_REF ,
	// "%"+searchkey+"%");
	// cri = Restrictions.or(cri, Restrictions.like(TaTpyFavorite.ATT_T_TITLE ,
	// "%"+searchkey+"%"));
	// cri = Restrictions.or(cri, Restrictions.like(TaTpyFavorite.ATT_T_ADDRESS ,
	// "%"+searchkey+"%"));
	// cri = Restrictions.and(cri, Restrictions.eq(TaTpyFavorite.ATT_I_STATUS ,
	// stat));
	//
	// List<TaTpyFavorite> areaLst = TaTpyFavorite.DAO.reqList(0, nbLineMax,
	// Order.asc(TaTpyFavorite.ATT_I_ID), cri);
	//
	// API.doResponse(response, JSONTool.reqJSonStringWithNull(
	// JSONDef.JS_OBJ_LOGGED , true,
	// JSONDef.JS_OBJ_SV_CODE , APICode.SV_CODE_API_YES,
	// JSONDef.JS_OBJ_RES_DATA , areaLst));
	// }
	//

	public static TaTpyFavorite reqNsoNewAll(TaAutUser user, JSONObject json) throws Exception {
		JSONObject          obj  = ToolData.reqJson(json, "obj", null);
		Map<String, Object> attr = API.reqMapParamsByClass(obj, TaTpyFavorite.class);

		TaTpyFavorite       ent  = new TaTpyFavorite(attr);

		if (!canWorkWithObj(user, WORK_NEW, ent)) { // other param after obj...
			return null;
		}

		ent.reqSet(TaTpyFavorite.ATT_I_AUT_USER, user.reqRef());
		ent.reqSet(TaTpyFavorite.ATT_I_TYPE, TaTpyFavorite.FAVORITE_TYPE_LIKE);
		ent.reqSet(TaTpyFavorite.ATT_D_DATE, new Date());

		// check exist favorite
		List<TaTpyFavorite> lst = TaTpyFavorite.DAO.reqList(Restrictions.eq(TaTpyFavorite.ATT_I_AUT_USER, user.reqId()),
				Restrictions.eq(TaTpyFavorite.ATT_I_ENTITY_TYPE, ent.req(TaTpyFavorite.ATT_I_ENTITY_TYPE)),
				Restrictions.eq(TaTpyFavorite.ATT_I_ENTITY_ID, ent.req(TaTpyFavorite.ATT_I_ENTITY_ID)));
		if (lst.size() > 0)
			return null;

		TaTpyFavorite.DAO.doPersist(ent);
		return ent;
	}

	public static boolean reqNsoRemove(TaAutUser user, JSONObject json) throws Exception {
		Integer parTyp = ToolData.reqInt(json, "parTyp", null);
		Integer parId  = ToolData.reqInt(json, "parId", null);

		if (parTyp == null || parId == null)
			return false;

		if (!canWorkWithObj(user, WORK_DEL)) { // other param after obj...
			return false;
		}

		// check exist favorite
		List<TaTpyFavorite> lst = TaTpyFavorite.DAO.reqList(Restrictions.eq(TaTpyFavorite.ATT_I_AUT_USER, user.reqId()),
				Restrictions.eq(TaTpyFavorite.ATT_I_ENTITY_TYPE, parTyp),
				Restrictions.eq(TaTpyFavorite.ATT_I_ENTITY_ID, parId));
		if (lst != null && lst.size() > 0) {
			TaTpyFavorite.DAO.doRemove(lst);
			return true;
		}

		return false;
	}

	private static void doPrjProjectNewFavorite(TaAutUser user, JSONObject json, HttpServletResponse response)
			throws Exception {
		// ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doGet --------------");
		TaTpyFavorite ent = reqNsoNewAll(user, json);

		if (ent == null) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}
		API.doResponse(response,
				ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_YES, DefJS.RES_DATA, ent));

	}

	private static void doPrjProjectRemoveFavorite(TaAutUser user, JSONObject json, HttpServletResponse response)
			throws Exception {
		// ToolLogServer.doLogDebug("--------- "+ SV_CLASS+ ".doGet --------------");
		boolean ok = reqNsoRemove(user, json);

		if (!ok) {
			API.doResponse(response, DefAPI.API_MSG_KO);
			return;
		}

		API.doResponse(response, ToolJSON.reqJSonString(DefJS.SESS_STAT, 1, DefJS.SV_CODE, DefAPI.SV_CODE_API_YES));
	}

	// -----------------------------------------------------------------------------------------------------------------------------------

}
