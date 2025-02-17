package com.hnv.db.prj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hnv.api.def.DefTime;
import com.hnv.api.main.Hnv_CfgHibernate;
import com.hnv.common.tool.ToolDBEntity;
import com.hnv.common.tool.ToolJSON;
import com.hnv.common.tool.ToolSet;
import com.hnv.common.util.CacheData;
import com.hnv.data.json.JSONArray;
import com.hnv.data.json.JSONObject;
import com.hnv.db.EntityAbstract;
import com.hnv.db.EntityDAO;
import com.hnv.db.aut.vi.ViAutUserMember;
import com.hnv.db.nso.TaNsoPost;
import com.hnv.db.tpy.TaTpyCategory;
import com.hnv.db.tpy.TaTpyDocument;
import com.hnv.db.tpy.TaTpyInformation;
import com.hnv.db.tpy.TaTpyRelationship;
import com.hnv.def.DefDBExt;		

/**
 * TaPrjProject by H&V SAS
 */
@Entity
@Table(name = DefDBExt.TA_PRJ_PROJECT )
public class TaPrjProject extends EntityAbstract<TaPrjProject> {

	private static final long serialVersionUID = 1L;
	public static final int STAT_PRJ_NEW 							= 0;
	public static final int STAT_PRJ_TODO 							= 10;
	public static final int STAT_PRJ_INPROGRESS 					= 20;
	public static final int STAT_PRJ_REVIEW 						= 30;
	public static final int STAT_PRJ_DONE 							= 40;
	public static final int STAT_PRJ_CLOSED 						= 50;
	public static final int STAT_PRJ_FAIL 							= 60;
	public static final int STAT_PRJ_UNRESOLVED 					= 70;
	
	public static final int STAT_PRJ_TEST_UNIT_TODO					= 10;
	public static final int STAT_PRJ_TEST_UNIT_EXECUTING			= 20;
	public static final int STAT_PRJ_TEST_UNIT_BLOCKED				= 30;
	public static final int STAT_PRJ_TEST_UNIT_PASS					= 40;
	public static final int STAT_PRJ_TEST_UNIT_FAIL					= 60;	
	public static final int STAT_PRJ_TEST_UNIT_ABORT				= 70;
	
	//-------------------------------------------------------------------------
	
	public static final int TYP00_PRJ_PROJECT 						= 10;
	public static final int TYP00_PRJ_DATACENTER 					= 20;
	public static final int TYP00_PRJ_SPRINT 						= 100;
	public static final int TYP00_PRJ_WORKFLOW 						= 200;
	
	public static final int TYP01_PRJ_IT 							= 11;
	public static final int TYP01_PRJ_COMMERCE						= 12;
	public static final int TYP01_PRJ_OTHER							= 15;
	
	public static final int TYP02_PRJ_MAIN 							= 0;
	public static final int TYP02_PRJ_SUB 							= 1;
	public static final int TYP02_PRJ_ELE 							= 2;
	//-------------------------------------------------------------------------

//	public static final int TYP02_PRJ_PROJECT 						= 0;
//	public static final int TYP02_PRJ_EPIC 							= 1;
//	public static final int TYP02_PRJ_TASK 							= 2;
//	
//	public static final int TYP02_PRJ_FOLDER_MAIN					= 10;
//	public static final int TYP02_PRJ_FOLDER_SUB					= 11;
	
	
	public static final int LEV_ROLE_PRJ_MANAGER 					= 0;
	public static final int LEV_ROLE_PRJ_REPORTER 					= 1;
	public static final int LEV_ROLE_PRJ_WORKER 					= 2;
	public static final int LEV_ROLE_PRJ_WATCHER 					= 3;

	public static final int TYP_LEV_BASE 							= 0;
	public static final int TYP_LEV_MEDIUM 							= 1;
	public static final int TYP_LEV_HIGH 							= 3;
	public static final int TYP_LEV_TOP 							= 4;
	
	public static final int TYP_WF_ENTITY_01 						= 20002;
	
	//----------------------------------------------------------------------------
	public static final int TYP00_PRJ_TEST							= 3;
	
	public static final int TYP01_PRJ_TEST_UNIT						= 1;
	public static final int TYP01_PRJ_TEST_GROUP					= 2;
	
	public static final int TYP02_PRJ_TEST_MANUAL					= 1;
	public static final int TYP02_PRJ_TEST_AUTO						= 2;
	
	
	
	//---------------------------List of Column from DB-----------------------------
	public static final String	COL_I_ID                              =	"I_ID";
	public static final String	COL_I_GROUP                           =	"I_Group";
	public static final String	COL_T_CODE                            =	"T_Code";
	public static final String	COL_T_NAME                            =	"T_Name";
	public static final String	COL_T_DESCRIPTION_01                  =	"T_Description_01";
	public static final String	COL_T_DESCRIPTION_02                  =	"T_Description_02";
	public static final String	COL_T_TAG                             =	"T_Tag";
	public static final String	COL_I_TYPE_00                         =	"I_Type_00";
	public static final String	COL_I_TYPE_01                         =	"I_Type_01";
	public static final String	COL_I_TYPE_02                         =	"I_Type_02";
	public static final String	COL_I_STATUS                          =	"I_Status";
	public static final String	COL_I_LEVEL                           =	"I_Level";
	public static final String	COL_D_DATE_NEW                        =	"D_Date_New";
	public static final String	COL_D_DATE_MOD                        =	"D_Date_Mod";
	public static final String	COL_D_DATE_BEGIN                      =	"D_Date_Begin";
	public static final String	COL_D_DATE_END                        =	"D_Date_End";
	public static final String	COL_I_AUT_USER_01                     =	"I_Aut_User_01";
	public static final String	COL_I_AUT_USER_02                     =	"I_Aut_User_02";
	public static final String	COL_I_PER_MANAGER                     =	"I_Per_Manager";
	public static final String	COL_I_PARENT                          =	"I_Parent";
	public static final String	COL_F_VAL_00                          =	"F_Val_00";
	public static final String	COL_F_VAL_01                          =	"F_Val_01";
	public static final String	COL_F_VAL_02                          =	"F_Val_02";
	public static final String	COL_F_VAL_03                          =	"F_Val_03";
	public static final String	COL_F_VAL_04                          =	"F_Val_04";
	public static final String	COL_F_VAL_05                          =	"F_Val_05";
	public static final String	COL_F_VAL_06                          =	"F_Val_06";
	public static final String	COL_F_VAL_07                          =	"F_Val_07";
	public static final String	COL_F_VAL_08                          =	"F_Val_08";
	public static final String	COL_F_VAL_09                          =	"F_Val_09";
	public static final String	COL_F_VAL_10                          =	"F_Val_10";


	//---------------------------List of ATTR of class-----------------------------
	public static final String	ATT_I_ID                              =	"I_ID";
	public static final String	ATT_I_GROUP                           =	"I_Group";
	public static final String	ATT_T_CODE                            =	"T_Code";
	public static final String	ATT_T_NAME                            =	"T_Name";
	public static final String	ATT_T_DESCRIPTION_01                  =	"T_Description_01";
	public static final String	ATT_T_DESCRIPTION_02                  =	"T_Description_02";
	public static final String	ATT_T_TAG                             =	"T_Tag";
	public static final String	ATT_I_TYPE_00                         =	"I_Type_00";
	public static final String	ATT_I_TYPE_01                         =	"I_Type_01";
	public static final String	ATT_I_TYPE_02                         =	"I_Type_02";
	public static final String	ATT_I_STATUS                          =	"I_Status";
	public static final String	ATT_I_LEVEL                           =	"I_Level";
	public static final String	ATT_D_DATE_NEW                        =	"D_Date_New";
	public static final String	ATT_D_DATE_MOD                        =	"D_Date_Mod";
	public static final String	ATT_D_DATE_BEGIN                      =	"D_Date_Begin";
	public static final String	ATT_D_DATE_END                        =	"D_Date_End";
	public static final String	ATT_I_AUT_USER_01                     =	"I_Aut_User_01";
	public static final String	ATT_I_AUT_USER_02                     =	"I_Aut_User_02";
	public static final String	ATT_I_PER_MANAGER                     =	"I_Per_Manager";
	public static final String	ATT_I_PARENT                          =	"I_Parent";
	public static final String	ATT_F_VAL_00                          =	"F_Val_00";
	public static final String	ATT_F_VAL_01                          =	"F_Val_01";
	public static final String	ATT_F_VAL_02                          =	"F_Val_02";
	public static final String	ATT_F_VAL_03                          =	"F_Val_03";
	public static final String	ATT_F_VAL_04                          =	"F_Val_04";
	public static final String	ATT_F_VAL_05                          =	"F_Val_05";
	public static final String	ATT_F_VAL_06                          =	"F_Val_06";
	public static final String	ATT_F_VAL_07                          =	"F_Val_07";
	public static final String	ATT_F_VAL_08                          =	"F_Val_08";
	public static final String	ATT_F_VAL_09                          =	"F_Val_09";
	public static final String	ATT_F_VAL_10                          =	"F_Val_10";

	public static final String	ATT_O_DOCUMENTS        	  			  =	"O_Documents";
	public static final String	ATT_O_AVATAR                      	  =	"O_Avatar";
	public static final String	ATT_O_MEMBERS                      	  =	"O_Members";
	public static final String	ATT_O_PROJECT                      	  =	"O_Project";
	public static final String	ATT_O_EPICS                      	  =	"O_Epics";
	public static final String	ATT_O_TASKS                      	  =	"O_Tasks";
	
	public static final String	ATT_O_COMMENTS                        =	"O_Comments";
	
	public static final String	ATT_O_USER_ROLE                       =	"O_User_Role";
	
	public static final String	ATT_O_EPIC_INFO                       =	"O_Epic_Info";
	public static final String	ATT_O_COUNT_EPIC                      =	"O_Count_Epic";
	public static final String	ATT_O_COUNT_TASK                      =	"O_Count_Task";
	public static final String	ATT_O_COUNT_EPIC_FILE                 =	"O_Count_Epic_File";
	
	public static final String	ATT_O_PARENT                      	  =	"O_Parent";
	
	public static final String	ATT_O_NB_EPIC_TASK_SIZE_DOC           =	"O_Nb_Epic_Task_Size_Doc";
	public static final String	ATT_O_NB_CHILD_SIZE_DOC               =	"O_Nb_Child_Size_Doc";

	//-------every entity class must initialize its DAO from here -----------------------------
	private 	static 	final boolean[] 			RIGHTS		= {true, true, true, true, false}; //canRead, canAdd, canUpd, canDel, del physique or flag only 
	private 	static	final boolean				API_CACHE 	= false;
	private 	static 	final boolean[]				HISTORY		= {false, false, false}; //add, mod, del

	public		static 	final EntityDAO<TaPrjProject> 	DAO;
	static{
		DAO = new EntityDAO<TaPrjProject>(Hnv_CfgHibernate.reqFactoryEMSession(Hnv_CfgHibernate.ID_FACT_MAIN) , TaTpyCategory.class, RIGHTS, HISTORY, DefDBExt.TA_PRJ_PROJECT, DefDBExt.ID_TA_PRJ_PROJECT);

	}

	//-----------------------Class Attributs-------------------------
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=COL_I_ID, nullable = false)
	private	Integer         I_ID;

	@Column(name=COL_I_GROUP, nullable = false)
	private	Integer         I_Group;

	@Column(name=COL_T_CODE, nullable = true)
	private	String          T_Code;

	@Column(name=COL_T_NAME, nullable = true)
	private	String          T_Name;

	@Column(name=COL_T_DESCRIPTION_01, nullable = true)
	private	String          T_Description_01;

	@Column(name=COL_T_DESCRIPTION_02, nullable = true)
	private	String          T_Description_02;

	@Column(name=COL_T_TAG, nullable = true)
	private	String          T_Tag;

	@Column(name=COL_I_TYPE_00, nullable = true)
	private	Integer         I_Type_00;
	
	@Column(name=COL_I_TYPE_01, nullable = true)
	private	Integer         I_Type_01;

	@Column(name=COL_I_TYPE_02, nullable = true)
	private	Integer         I_Type_02;

	@Column(name=COL_I_STATUS, nullable = true)
	private	Integer         I_Status;

	@Column(name=COL_I_LEVEL, nullable = true)
	private	Integer         I_Level;

	@Column(name=COL_D_DATE_NEW, nullable = true)
	private	Date            D_Date_New;

	@Column(name=COL_D_DATE_MOD, nullable = true)
	private	Date            D_Date_Mod;

	@Column(name=COL_D_DATE_BEGIN, nullable = true)
	private	Date            D_Date_Begin;

	@Column(name=COL_D_DATE_END, nullable = true)
	private	Date            D_Date_End;

	@Column(name=COL_I_AUT_USER_01, nullable = true)
	private	Integer         I_Aut_User_01;

	@Column(name=COL_I_AUT_USER_02, nullable = true)
	private	Integer         I_Aut_User_02;

	@Column(name=COL_I_PER_MANAGER, nullable = true)
	private	Integer         I_Per_Manager;

	@Column(name=COL_I_PARENT, nullable = true)
	private	Integer         I_Parent;

	@Column(name=COL_F_VAL_00, nullable = true)
	private	Double          F_Val_00;
	
	@Column(name=COL_F_VAL_01, nullable = true)
	private	Double          F_Val_01;

	@Column(name=COL_F_VAL_02, nullable = true)
	private	Double          F_Val_02;

	@Column(name=COL_F_VAL_03, nullable = true)
	private	Double          F_Val_03;

	@Column(name=COL_F_VAL_04, nullable = true)
	private	Double          F_Val_04;

	@Column(name=COL_F_VAL_05, nullable = true)
	private	Double          F_Val_05;

	@Column(name=COL_F_VAL_06, nullable = true)
	private	Double          F_Val_06;

	@Column(name=COL_F_VAL_07, nullable = true)
	private	Double          F_Val_07;

	@Column(name=COL_F_VAL_08, nullable = true)
	private	Double          F_Val_08;

	@Column(name=COL_F_VAL_09, nullable = true)
	private	Double          F_Val_09;


	//-----------------------Transient Variables-------------------------
	@Transient
	private List<TaTpyDocument>			O_Documents			= null;

	@Transient
	private TaTpyDocument				O_Avatar			= null;

	@Transient
	private List<ViAutUserMember>		O_Members			= null;

	@Transient
	private TaPrjProject			    O_Project			= null;
	
	@Transient
	private List<TaPrjProject>			O_Epics				= null;

	@Transient
	private List<TaPrjProject>			O_Tasks				= null;
	
	@Transient
	private List<TaTpyInformation>		O_History			= null;
	
	@Transient
	private List<TaNsoPost>				O_Comments			= null;
	
	@Transient
	private Integer						O_User_Role			= null;
	
	@Transient
	private List<TaPrjProject>			O_Epic_Info			= null;
	
	@Transient
	private Integer						O_Count_Epic		= null;
	
	@Transient
	private Integer						O_Count_Task		= null;
	
	@Transient
	private Integer						O_Count_Epic_File	= null;
	
	@Transient
	private TaPrjProject			    O_Parent			= null;
	
	@Transient
	private Double[]			    	O_Nb_Epic_Task_Size_Doc			= null;
	
	@Transient
	private Double[]			    	O_Nb_Child_Size_Doc				= null;
	
	
	//---------------------Constructeurs-----------------------
	public TaPrjProject(){}

	public TaPrjProject(Map<String, Object> attrs) throws Exception {
		this.reqSetAttr(attrs);
		doInitDBFlag();
	}

	public TaPrjProject(Integer I_Group) throws Exception {
		this.reqSetAttr(
				ATT_I_GROUP      , I_Group
				);
		doInitDBFlag();
	}
	public TaPrjProject(Integer I_Group, String T_Code, String T_Name, String T_Description_01, String T_Description_02, String T_Tag, Integer I_Type_01, Integer I_Type_02, Integer I_Status, Integer I_Level, Date D_Date_New, Date D_Date_Mod, Date D_Date_Begin, Date D_Date_End, Integer I_Aut_User_01, Integer I_Aut_User_02, Integer I_Per_Manager, Integer I_Parent, Double F_Val_01, Double F_Val_02, Double F_Val_03, Double F_Val_04, Double F_Val_05) throws Exception {
		this.reqSetAttr(
				ATT_I_GROUP                , I_Group,
				ATT_T_CODE                 , T_Code,
				ATT_T_NAME                 , T_Name,
				ATT_T_DESCRIPTION_01       , T_Description_01,
				ATT_T_DESCRIPTION_02       , T_Description_02,
				ATT_T_TAG                  , T_Tag,
				ATT_I_TYPE_01              , I_Type_01,
				ATT_I_TYPE_02              , I_Type_02,
				ATT_I_STATUS               , I_Status,
				ATT_I_LEVEL                , I_Level,
				ATT_D_DATE_NEW             , D_Date_New,
				ATT_D_DATE_MOD             , D_Date_Mod,
				ATT_D_DATE_BEGIN           , D_Date_Begin,
				ATT_D_DATE_END             , D_Date_End,
				ATT_I_AUT_USER_01          , I_Aut_User_01,
				ATT_I_AUT_USER_02          , I_Aut_User_02,
				ATT_I_PER_MANAGER          , I_Per_Manager,
				ATT_I_PARENT               , I_Parent,
				ATT_F_VAL_00               , F_Val_00,
				ATT_F_VAL_01               , F_Val_01,
				ATT_F_VAL_02               , F_Val_02,
				ATT_F_VAL_03               , F_Val_03,
				ATT_F_VAL_04               , F_Val_04,
				ATT_F_VAL_05               , F_Val_05
				);
		doInitDBFlag();
	}


	//---------------------EntityInterface-----------------------
	@Override
	public Serializable reqRef() {
		return this.I_ID;

	}

	@Override
	public void doMergeWith(TaPrjProject ent) {
		if (ent == this) return;
		this.I_Group                = ent.I_Group;
		this.T_Code                 = ent.T_Code;
		this.T_Name                 = ent.T_Name;
		this.T_Description_01       = ent.T_Description_01;
		this.T_Description_02       = ent.T_Description_02;
		this.T_Tag                  = ent.T_Tag;
		this.I_Type_01              = ent.I_Type_01;
		this.I_Type_02              = ent.I_Type_02;
		this.I_Status               = ent.I_Status;
		this.I_Level                = ent.I_Level;
		this.D_Date_New             = ent.D_Date_New;
		this.D_Date_Mod             = ent.D_Date_Mod;
		this.D_Date_Begin           = ent.D_Date_Begin;
		this.D_Date_End             = ent.D_Date_End;
		this.I_Aut_User_01          = ent.I_Aut_User_01;
		this.I_Aut_User_02          = ent.I_Aut_User_02;
		this.I_Per_Manager          = ent.I_Per_Manager;
		this.I_Parent               = ent.I_Parent;
		this.F_Val_00               = ent.F_Val_00;
		this.F_Val_01               = ent.F_Val_01;
		this.F_Val_02               = ent.F_Val_02;
		this.F_Val_03               = ent.F_Val_03;
		this.F_Val_04               = ent.F_Val_04;
		this.F_Val_05               = ent.F_Val_05;



		//---------------------Merge Transient Variables if exist-----------------------
	}

	@Override
	public boolean equals(Object o)  {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		boolean ok = false;

		ok = (I_ID == ((TaPrjProject)o).I_ID);
		if (!ok) return ok;


		if (!ok) return ok;
		return ok;
	}

	@Override
	public int hashCode() {
		return this.I_ID;

	}

	@Override
	public String toString() {
		return 	"TaPrjProject { " +
				"I_ID:"+                      I_ID +"," + 
				"I_Group:"+                   I_Group +"," + 
				"T_Code:"+                    T_Code +"," + 
				"T_Name:"+                    T_Name +"," + 
				"T_Description_01:"+          T_Description_01 +"," + 
				"T_Description_02:"+          T_Description_02 +"," + 
				"T_Tag:"+                     T_Tag +"," + 
				"I_Type_01:"+                 I_Type_01 +"," + 
				"I_Type_02:"+                 I_Type_02 +"," + 
				"I_Status:"+                  I_Status +"," + 
				"I_Level:"+                   I_Level +"," + 
				"D_Date_New:"+                D_Date_New +"," + 
				"D_Date_Mod:"+                D_Date_Mod +"," + 
				"D_Date_Begin:"+              D_Date_Begin +"," + 
				"D_Date_End:"+                D_Date_End +"," + 
				"I_Aut_User_01:"+             I_Aut_User_01 +"," + 
				"I_Aut_User_02:"+             I_Aut_User_02 +"," + 
				"I_Per_Manager:"+             I_Per_Manager +"," + 
				"I_Parent:"+                  I_Parent +"," + 
				"F_Val_00:"+                  F_Val_00 +"," + 
				"F_Val_01:"+                  F_Val_01 +"," + 
				"F_Val_02:"+                  F_Val_02 +"," + 
				"F_Val_03:"+                  F_Val_03 +"," + 
				"F_Val_04:"+                  F_Val_04 +"," + 
				"F_Val_05:"+                  F_Val_05 +"," + 
				"}";

	}

	public void doBuildDocuments(boolean forced) {
		if (!forced && this.O_Documents != null) return;

		try {
			this.O_Documents = TaTpyDocument.DAO.reqList(
					Restrictions.eq(TaTpyDocument.ATT_I_ENTITY_TYPE	, DefDBExt.ID_TA_PRJ_PROJECT),
					Restrictions.eq(TaTpyDocument.ATT_I_ENTITY_ID	, this.I_ID)
//					Restrictions.eq(TaTpyDocument.ATT_I_TYPE_01	, ServiceTpyDocument.TYPE_01_FILE)
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doBuildAvatar(boolean forced) {
		if (!forced && this.O_Avatar != null) return;

		try {
			this.O_Avatar = TaTpyDocument.DAO.reqEntityByValues(
					TaTpyDocument.ATT_I_ENTITY_TYPE	, DefDBExt.ID_TA_PRJ_PROJECT,
					TaTpyDocument.ATT_I_ENTITY_ID	, this.I_ID,
					TaTpyDocument.ATT_I_TYPE_01		, TaTpyDocument.TYPE_01_FILE_MEDIA,
					TaTpyDocument.ATT_I_TYPE_02		, TaTpyDocument.TYPE_02_FILE_IMG_AVATAR);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static CacheData<Object> 	cache_entity= new CacheData<Object>	(500, DefTime.TIME_00_30_00_000);
	public void doBuildEpicInfo(boolean forced) throws Exception {
		if (!forced && this.O_Epic_Info != null) return;

		String 				key 	= this.I_Group + "_EPICS";
		List<TaPrjProject> 	epics 	= (List<TaPrjProject>) cache_entity.reqData(key);	//we need cache because every task, epic in project request this
		if (forced || epics == null) {
			Set<Integer> typeEpics	= new HashSet<Integer>() ;
			typeEpics.add(TYP02_PRJ_MAIN);
			typeEpics.add(TYP02_PRJ_SUB);
			
			epics = TaPrjProject.DAO.reqList(Order.asc(TaPrjProject.ATT_I_TYPE_02),
					Restrictions.eq(TaPrjProject.ATT_I_GROUP	, this.I_Group),
					Restrictions.in(TaPrjProject.ATT_I_TYPE_02	, typeEpics)
					);
			if (epics!=null && epics.size()>0) {	
				Hashtable	mapEpics 	= ToolDBEntity.reqTab( epics, TaPrjProject.ATT_I_ID);
				
				//reset 0
				for (TaPrjProject epic : epics) {
					epic.O_Count_Task = 0;
					epic.O_Count_Epic = 0;
					epic.O_Count_Epic_File = 0;
				}
				
				List<TaPrjProject> 	tasks 	=  TaPrjProject.DAO.reqList(
						Restrictions.eq(TaPrjProject.ATT_I_GROUP	, this.I_Group),
						Restrictions.eq(TaPrjProject.ATT_I_TYPE_02	, TYP02_PRJ_ELE)
						);
				//--compute count task for each epic
				for (TaPrjProject ent : tasks) {
					Integer 		iParent = ent.reqInt(ent, TaPrjProject.ATT_I_PARENT);
					TaPrjProject 	epic 	= iParent!=null?(TaPrjProject) mapEpics.get(iParent):null;
					if (epic!=null) {
						epic.O_Count_Task ++;
					}
				}
				
				for (TaPrjProject ent : epics) {
					Integer 		iParent = ent.reqInt(ent	, TaPrjProject.ATT_I_PARENT);
					TaPrjProject 	epic 	= iParent!=null?(TaPrjProject) mapEpics.get(iParent):null;
					if (epic!=null) {
						epic.O_Count_Epic ++;
					}
				}
				
				for (TaPrjProject epic : epics) {
					Integer 		epicId  = epic.reqInt(epic	, TaPrjProject.ATT_I_ID);
					epic.O_Count_Epic_File =  TaTpyDocument.DAO.reqCount(
							Restrictions.eq(TaTpyDocument.ATT_I_ENTITY_ID	, epicId),
							Restrictions.eq(TaTpyDocument.ATT_I_ENTITY_TYPE	, DefDBExt.ID_TA_PRJ_PROJECT)).intValue();;
				}
				cache_entity.reqPut(key, epics);
			}			
		}else {				
			cache_entity.doRefresh(key); 
		}
		
		this.O_Epic_Info  = epics;
	}
	

	public void doBuildEpics(boolean forced) {
		if (!forced && this.O_Epics != null) return;

		try {
			this.O_Epics = TaPrjProject.DAO.reqList(
					Restrictions.eq(TaPrjProject.ATT_I_PARENT	, this.I_ID),
					Restrictions.eq(TaPrjProject.ATT_I_TYPE_02	, TYP02_PRJ_SUB)
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doBuildTasks(boolean forced) {
		if (!forced && this.O_Tasks != null) return;

		try {
			this.O_Tasks = TaPrjProject.DAO.reqList(
					Restrictions.eq(TaPrjProject.ATT_I_PARENT	, this.I_ID),
					Restrictions.eq(TaPrjProject.ATT_I_TYPE_02	, TYP02_PRJ_ELE)
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doBuildTasksSprint(boolean forced) {
		if (!forced && this.O_Tasks != null) return;

		try {
			String strIds  = this.T_Description_02;
			if(strIds == null) return;
			JSONObject ids 		= ToolJSON.reqJSonObjectFromString(strIds);
			JSONArray  task_ids = (JSONArray) ids.get("task_ids");
			
			Set<Integer> setIds = new HashSet<Integer>();
			for(int i=0; i< task_ids.size(); i++) {
				setIds.add((int)(long) task_ids.get(i));
			}
			
			this.O_Tasks = TaPrjProject.DAO.reqList_In(TaPrjProject.ATT_I_ID, setIds, 
					Restrictions.eq(TaPrjProject.ATT_I_GROUP	, this.I_Group),
					Restrictions.eq(TaPrjProject.ATT_I_TYPE_02	, TYP02_PRJ_ELE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doBuildTestGroup(boolean forced) {
		if (!forced && this.O_Tasks != null) return;

		try {
			String strIds  = this.T_Description_02;
			if(strIds == null) return;
			JSONObject ids 		= ToolJSON.reqJSonObjectFromString(strIds);
			JSONArray  task_ids = (JSONArray) ids.get("task_ids");
			
			Set<Integer> setIds = new HashSet<Integer>();
			for(int i=0; i< task_ids.size(); i++) {
				setIds.add((int)(long) task_ids.get(i));
			}
			
			this.O_Tasks = TaPrjProject.DAO.reqList_In(TaPrjProject.ATT_I_ID, setIds, 
					Restrictions.eq(TaPrjProject.ATT_I_GROUP	, this.I_Group),
					Restrictions.eq(TaPrjProject.ATT_I_TYPE_00	, TYP00_PRJ_TEST),
					Restrictions.eq(TaPrjProject.ATT_I_TYPE_01	, TYP01_PRJ_TEST_UNIT));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doBuildTestGroupHistory(boolean forced) {
		if (!forced && this.O_History != null) return;

		try {
			this.O_History = TaTpyInformation.DAO.reqList(Restrictions.and(
					Restrictions.eq(TaTpyInformation.ATT_I_ENTITY_ID	, this.I_ID),
					Restrictions.eq(TaTpyInformation.ATT_I_ENTITY_TYPE	, TYP00_PRJ_TEST)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doAddDocument(TaTpyDocument doc) throws Exception {
		if (O_Documents==null){
			O_Documents = new ArrayList<TaTpyDocument>();
		}
		O_Documents.add(doc);
	}
	
	public void doBuildParent() throws Exception{
		
	}

	public Integer reqId() {
		return this.I_ID;
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static List<TaPrjProject> reqListPrjFilter(int begin, int countRow, String orderCol, String orderDir, Criterion cri) throws Exception {
		List<TaPrjProject> result = null;

//		Criterion cri = reqCri(searchKey, manId, uId ,typ01, typ02, status, valMin, valMax, group, stat, searchUser);
		
		Order 		order 	= null;
		List<Order> orders 	= null;
		if (orderCol!=null && orderDir!=null ) {
			if 		(orderDir.equals("DESC")) order = Order.desc(orderCol);
			else if (orderDir.equals("ASC"))  order = Order.asc(orderCol);
		} else {
			orders = new ArrayList<Order>();
			orders.add(Order.desc(TaPrjProject.ATT_I_LEVEL));
			orders.add(Order.asc(TaPrjProject.ATT_D_DATE_END));
		}
		
		if (order!=null)
			result = TaPrjProject.DAO.reqList(begin, countRow, order, cri);
		else 
			result = TaPrjProject.DAO.reqList(begin, countRow, orders, cri);
		
		return result;
	}


	private static Criterion reqRestrictionFromNameCodeTag(List<String> searchKey) {	
		if (searchKey==null || searchKey.size()==0) return null;
		
		Criterion cri = null;
		for (String s: searchKey){	
			if (s.equals("%")) continue;
			if (cri ==null) 
				cri = Restrictions.or(
						Restrictions.ilike(TaPrjProject.ATT_T_NAME, s), 
						Restrictions.ilike(TaPrjProject.ATT_T_CODE, s),
						Restrictions.ilike(TaPrjProject.ATT_T_TAG, s));
			else {
				cri = Restrictions.and(cri, Restrictions.or(
						Restrictions.ilike(TaPrjProject.ATT_T_NAME, s), 
						Restrictions.ilike(TaPrjProject.ATT_T_CODE, s),
						Restrictions.ilike(TaPrjProject.ATT_T_TAG, s)));
			}
		}

		return cri;
	}
	
	private static Criterion reqRestrictionFromUsername(List<String> searchKey) throws Exception {	
		if (searchKey==null || searchKey.size()==0) return null;
		
		Criterion cri = null;
		for (String s: searchKey){	
			if (s.equals("%")) continue;
			if (cri ==null) 
				cri = Restrictions.ilike(ViAutUserMember.ATT_T_LOGIN_01, "%" + s + "%");
			else {
				cri = Restrictions.and(cri, Restrictions.ilike(ViAutUserMember.ATT_T_LOGIN_01, "%" + s + "%"));
			}
		}

		if (cri!=null) {
			Set<Integer> 		setUids = new HashSet<Integer>();
			List<ViAutUserMember> 	users 	= ViAutUserMember.DAO.reqList(cri);
			if(users != null && users.size() > 0) {
				setUids = ToolSet.reqSetInt(users, ViAutUserMember.ATT_I_ID);
			}
			
			if (setUids!=null && setUids.size()>0) {
				Criterion criU = Restrictions.and(
						Restrictions.eq(TaTpyRelationship.ATT_I_ENTITY_TYPE_01	, DefDBExt.ID_TA_PRJ_PROJECT),
						Restrictions.eq(TaTpyRelationship.ATT_I_ENTITY_TYPE_02	, DefDBExt.ID_TA_AUT_USER),
						Restrictions.in(TaTpyRelationship.ATT_I_ENTITY_ID_02	, setUids));
				List<TaTpyRelationship> rel = TaTpyRelationship.DAO.reqList(criU);
				Set<Integer> 			ids = ToolSet.reqSetInt(rel, TaTpyRelationship.ATT_I_ENTITY_ID_01);
				if (ids!=null && ids.size()>0)
					cri = Restrictions.in(TaPrjProject.ATT_I_ID, ids);
				else 
					cri = null;
			}else cri = null;
		}
		return cri;
	}


	public static Number reqCountPrjFilter(Criterion cri) throws Exception {
		return TaPrjProject.DAO.reqCount(cri);
	}

	public static Criterion reqCri(List<String> searchKey, Integer manId, Integer uId, Integer typ00, Integer typ01, Integer typ02, JSONArray status, Double valMin, Double valMax, Integer group, Integer stat) throws Exception{
		Criterion cri = Restrictions.eq(TaPrjProject.ATT_I_PER_MANAGER, manId);

		if(typ00 != null)
			cri = Restrictions.and(cri, Restrictions.eq(TaPrjProject.ATT_I_TYPE_00, typ00));
		
		if(typ01 != null)
			cri = Restrictions.and(cri, Restrictions.eq(TaPrjProject.ATT_I_TYPE_01, typ01));

		if(typ02 != null)
			cri = Restrictions.and(cri, Restrictions.eq(TaPrjProject.ATT_I_TYPE_02, typ02));

		if(status != null && status.size() > 0) {
			Set<Integer> setStat = new HashSet<Integer>();
			for(int i = 0 ; i < status.size(); i++) {
				setStat.add((int)(long)status.get(i));
			}
			cri = Restrictions.and(cri, Restrictions.in(TaPrjProject.ATT_I_STATUS, setStat));
//			cri = Restrictions.and(cri, Restrictions.eq(TaPrjProject.ATT_I_STATUS, status));
		}
			
		if(stat != null) {
			cri = Restrictions.and(cri, Restrictions.eq(TaPrjProject.ATT_I_STATUS, stat));
		}
		
		if(group != null)
			cri = Restrictions.and(cri, Restrictions.eq(TaPrjProject.ATT_I_GROUP, group));

		
		if (valMin!=null) {
			cri = Restrictions.and(cri, Restrictions.or(
					Restrictions.ge(TaPrjProject.ATT_F_VAL_01, valMin),
					Restrictions.ge(TaPrjProject.ATT_F_VAL_02, valMin),
					Restrictions.ge(TaPrjProject.ATT_F_VAL_03, valMin),
					Restrictions.ge(TaPrjProject.ATT_F_VAL_04, valMin),
					Restrictions.ge(TaPrjProject.ATT_F_VAL_05, valMin)
					));
		}

		if (valMax!=null) {
			cri = Restrictions.and(cri, Restrictions.or(
					Restrictions.le(TaPrjProject.ATT_F_VAL_01, valMax),
					Restrictions.le(TaPrjProject.ATT_F_VAL_02, valMax),
					Restrictions.le(TaPrjProject.ATT_F_VAL_03, valMax),
					Restrictions.le(TaPrjProject.ATT_F_VAL_04, valMax),
					Restrictions.le(TaPrjProject.ATT_F_VAL_05, valMax)
					));
		}
		
		//--- with user view list task
		Criterion criUserMain	= null;
		if (uId !=null) {
			Criterion criU = Restrictions.and(
					Restrictions.eq(TaTpyRelationship.ATT_I_ENTITY_TYPE_01	, DefDBExt.ID_TA_PRJ_PROJECT),
					Restrictions.eq(TaTpyRelationship.ATT_I_ENTITY_TYPE_02	, DefDBExt.ID_TA_AUT_USER),
					Restrictions.eq(TaTpyRelationship.ATT_I_ENTITY_ID_02	, uId));
			List<TaTpyRelationship> rel = TaTpyRelationship.DAO.reqList(criU);
			
			Set<Integer> ids = ToolSet.reqSetInt(rel, TaTpyRelationship.ATT_I_ENTITY_ID_01);
			
			if (ids!=null && ids.size()>0)
				criUserMain = Restrictions.or(Restrictions.eq(TaPrjProject.ATT_I_AUT_USER_01, uId), Restrictions.in(TaPrjProject.ATT_I_ID, ids));
			else 
				criUserMain = Restrictions.eq(TaPrjProject.ATT_I_AUT_USER_01, uId);
		}
		if (criUserMain!=null) 
			cri = Restrictions.and(cri, criUserMain);
		
		//--- filter list user search
		Criterion criUserSearch = reqRestrictionFromUsername (searchKey);		

		//----combine with search project by keyword-----
		Criterion criKey =   reqRestrictionFromNameCodeTag (searchKey);		
		
		if (criKey !=null && criUserSearch!=null)
			cri = Restrictions.and(cri, Restrictions.or(criKey,criUserSearch));
		else if (criKey !=null)
			cri = Restrictions.and(cri, criKey);
		else if (criUserSearch !=null)
			cri = Restrictions.and(cri, criUserSearch);
		
		return cri;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------------
	public static List<TaPrjProject> reqListPrjLate(int begin, int countRow, String orderCol, String orderDir, List<String> searchKey, Integer manId, Integer uId, Integer typ00, Integer typ01, Integer typ02) throws Exception {
		List<TaPrjProject> result = null;

		Criterion cri = reqCriLate(searchKey, manId, uId ,typ00, typ01, typ02);
		
		Order order = null;
		if (orderCol!=null && orderDir!=null )
			if 		(orderDir.equals("DESC")) order = Order.desc(orderCol);
			else if (orderDir.equals("ASC"))  order = Order.asc(orderCol);

		if (order!=null)
			result = TaPrjProject.DAO.reqList(begin, countRow, order, cri);
		else 
			result = TaPrjProject.DAO.reqList(begin, countRow, cri);

		return result;
	}
	
	public static Number reqCountPrjLate(List<String> searchKey, Integer manId, Integer uId , Integer typ00, Integer typ01, Integer typ02) throws Exception {
		Criterion cri = reqCriLate(searchKey, manId, uId ,typ00, typ01, typ02);
		
		return TaPrjProject.DAO.reqCount(cri);
	}
	
	private static Criterion reqCriLate(List<String> searchKey, Integer manId, Integer uId , Integer typ00, Integer typ01, Integer typ02) throws Exception{
		Criterion cri = Restrictions.eq(TaPrjProject.ATT_I_PER_MANAGER, manId);

		if(typ00 != null)
			cri = Restrictions.and(cri, Restrictions.eq(TaPrjProject.ATT_I_TYPE_00, typ00));
		
		if(typ01 != null)
			cri = Restrictions.and(cri, Restrictions.eq(TaPrjProject.ATT_I_TYPE_01, typ01));

		if(typ02 != null)
			cri = Restrictions.and(cri, Restrictions.eq(TaPrjProject.ATT_I_TYPE_02, typ02));

		List<Integer> stats = Arrays.asList(TaPrjProject.STAT_PRJ_TODO, TaPrjProject.STAT_PRJ_INPROGRESS, TaPrjProject.STAT_PRJ_NEW, TaPrjProject.STAT_PRJ_REVIEW);
		cri = Restrictions.and(cri, Restrictions.in(TaPrjProject.ATT_I_STATUS, stats));

		Criterion criKey =   reqRestrictionFromNameCodeTag (searchKey);
		if (criKey !=null)
			cri = Restrictions.and(cri, criKey);

		
		Date dtMax = new Date();
		cri = Restrictions.and(cri, Restrictions.le(TaPrjProject.ATT_D_DATE_END		, dtMax));

		Criterion criU = null;
		if (uId !=null) {
			criU = Restrictions.and(
					Restrictions.eq(TaTpyRelationship.ATT_I_ENTITY_TYPE_01	, DefDBExt.ID_TA_PRJ_PROJECT),
					Restrictions.eq(TaTpyRelationship.ATT_I_ENTITY_TYPE_02	, DefDBExt.ID_TA_AUT_USER),
					Restrictions.eq(TaTpyRelationship.ATT_I_ENTITY_ID_02	, uId));
			List<TaTpyRelationship> rel = TaTpyRelationship.DAO.reqList(criU);
			Set<Integer> 			ids = ToolSet.reqSetInt(rel, TaTpyRelationship.ATT_I_ENTITY_ID_01);
			if (ids!=null && ids.size()>0)
				criU = Restrictions.or(Restrictions.eq(TaPrjProject.ATT_I_AUT_USER_01, uId), Restrictions.in(TaPrjProject.ATT_I_ID, ids));
			else 
				criU = Restrictions.eq(TaPrjProject.ATT_I_AUT_USER_01, uId);
		}

		if (criU!=null) cri = Restrictions.and(criU, cri);
		return cri;
	}
	
	public  void doAddChild(TaPrjProject prj) {
		if (this.O_Epics==null) this.O_Epics = new ArrayList<TaPrjProject>();
		this.O_Epics.add(prj);
	}
}
