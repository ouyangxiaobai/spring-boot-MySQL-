<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="规章制度"
				source_table="article"
			  ></list_result_search>
				<!-- 论坛搜索结果 -->
			  <list_result_search
				:list="result_forum"
				title="交流园地"
				source_table="forum"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/registered_user/list', 'get')"
				:list="result_registered_user_user_name"
				title="注册用户用户名"
				source_table="registered_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/instrument_classification/list', 'get')"
				:list="result_instrument_classification_instrument_category"
				title="仪器分类仪器类别"
				source_table="instrument_classification"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/instruments_and_equipment/list', 'get')"
				:list="result_instruments_and_equipment_instrument_name"
				title="仪器设备仪器名称"
				source_table="instruments_and_equipment"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/instruments_and_equipment/list', 'get')"
				:list="result_instruments_and_equipment_instrument_category"
				title="仪器设备仪器类别"
				source_table="instruments_and_equipment"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/instruments_and_equipment/list', 'get')"
				:list="result_instruments_and_equipment_state"
				title="仪器设备状态"
				source_table="instruments_and_equipment"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/instrument_reservation/list', 'get')"
				:list="result_instrument_reservation_instrument_name"
				title="仪器预约仪器名称"
				source_table="instrument_reservation"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/instrument_reservation/list', 'get')"
				:list="result_instrument_reservation_instrument_category"
				title="仪器预约仪器类别"
				source_table="instrument_reservation"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/instrument_reservation/list', 'get')"
				:list="result_instrument_reservation_appointment_date"
				title="仪器预约预约使用日期"
				source_table="instrument_reservation"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/data_classification/list', 'get')"
				:list="result_data_classification_data_type"
				title="资料分类资料类型"
				source_table="data_classification"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/learning_materials/list', 'get')"
				:list="result_learning_materials_data_name"
				title="学习资料资料名称"
				source_table="learning_materials"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/learning_materials/list', 'get')"
				:list="result_learning_materials_data_type"
				title="学习资料资料类型"
				source_table="learning_materials"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
	  "result_forum": [],
			"result_registered_user_user_name":[],
			"result_instrument_classification_instrument_category":[],
			"result_instruments_and_equipment_instrument_name":[],
			"result_instruments_and_equipment_instrument_category":[],
			"result_instruments_and_equipment_state":[],
			"result_instrument_reservation_instrument_name":[],
			"result_instrument_reservation_instrument_category":[],
			"result_instrument_reservation_appointment_date":[],
			"result_data_classification_data_type":[],
			"result_learning_materials_data_name":[],
			"result_learning_materials_data_type":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取交流园地
	 */
	get_forum() {
	  this.$get("~/api/forum/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_forum = json.result.list;
		}
	  });
	},
	/**
	 * 获取user_name
	 */
	get_registered_user_user_name(){
		this.$get("~/api/registered_user/get_list?like=0", { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_registered_user_user_name = json.result.list;
			result_registered_user_user_name.map(o => o.title = o['user_name'])
	  			this.result_registered_user_user_name = result_registered_user_user_name
		 	}
		});
	},
	/**
	 * 获取instrument_category
	 */
	get_instrument_classification_instrument_category(){
		this.$get("~/api/instrument_classification/get_list?like=0", { page: 1, size: 10, "instrument_category": this.query.word }, (json) => {
		  if (json.result) {
			var result_instrument_classification_instrument_category = json.result.list;
			result_instrument_classification_instrument_category.map(o => o.title = o['instrument_category'])
	  			this.result_instrument_classification_instrument_category = result_instrument_classification_instrument_category
		 	}
		});
	},
	/**
	 * 获取instrument_name
	 */
	get_instruments_and_equipment_instrument_name(){
		this.$get("~/api/instruments_and_equipment/get_list?like=0", { page: 1, size: 10, "instrument_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_instruments_and_equipment_instrument_name = json.result.list;
			result_instruments_and_equipment_instrument_name.map(o => o.title = o['instrument_name'])
	  			this.result_instruments_and_equipment_instrument_name = result_instruments_and_equipment_instrument_name
		 	}
		});
	},
	/**
	 * 获取instrument_category
	 */
	get_instruments_and_equipment_instrument_category(){
		this.$get("~/api/instruments_and_equipment/get_list?like=0", { page: 1, size: 10, "instrument_category": this.query.word }, (json) => {
		  if (json.result) {
			var result_instruments_and_equipment_instrument_category = json.result.list;
			result_instruments_and_equipment_instrument_category.map(o => o.title = o['instrument_category'])
	  			this.result_instruments_and_equipment_instrument_category = result_instruments_and_equipment_instrument_category
		 	}
		});
	},
	/**
	 * 获取state
	 */
	get_instruments_and_equipment_state(){
		this.$get("~/api/instruments_and_equipment/get_list?like=0", { page: 1, size: 10, "state": this.query.word }, (json) => {
		  if (json.result) {
			var result_instruments_and_equipment_state = json.result.list;
			result_instruments_and_equipment_state.map(o => o.title = o['state'])
	  			this.result_instruments_and_equipment_state = result_instruments_and_equipment_state
		 	}
		});
	},
	/**
	 * 获取instrument_name
	 */
	get_instrument_reservation_instrument_name(){
		this.$get("~/api/instrument_reservation/get_list?like=0", { page: 1, size: 10, "instrument_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_instrument_reservation_instrument_name = json.result.list;
			result_instrument_reservation_instrument_name.map(o => o.title = o['instrument_name'])
	  			this.result_instrument_reservation_instrument_name = result_instrument_reservation_instrument_name
		 	}
		});
	},
	/**
	 * 获取instrument_category
	 */
	get_instrument_reservation_instrument_category(){
		this.$get("~/api/instrument_reservation/get_list?like=0", { page: 1, size: 10, "instrument_category": this.query.word }, (json) => {
		  if (json.result) {
			var result_instrument_reservation_instrument_category = json.result.list;
			result_instrument_reservation_instrument_category.map(o => o.title = o['instrument_category'])
	  			this.result_instrument_reservation_instrument_category = result_instrument_reservation_instrument_category
		 	}
		});
	},
	/**
	 * 获取appointment_date
	 */
	get_instrument_reservation_appointment_date(){
		this.$get("~/api/instrument_reservation/get_list?like=0", { page: 1, size: 10, "appointment_date": this.query.word }, (json) => {
		  if (json.result) {
			var result_instrument_reservation_appointment_date = json.result.list;
			result_instrument_reservation_appointment_date.map(o => o.title = o['appointment_date'])
	  			this.result_instrument_reservation_appointment_date = result_instrument_reservation_appointment_date
		 	}
		});
	},
	/**
	 * 获取data_type
	 */
	get_data_classification_data_type(){
		this.$get("~/api/data_classification/get_list?like=0", { page: 1, size: 10, "data_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_data_classification_data_type = json.result.list;
			result_data_classification_data_type.map(o => o.title = o['data_type'])
	  			this.result_data_classification_data_type = result_data_classification_data_type
		 	}
		});
	},
	/**
	 * 获取data_name
	 */
	get_learning_materials_data_name(){
		this.$get("~/api/learning_materials/get_list?like=0", { page: 1, size: 10, "data_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_learning_materials_data_name = json.result.list;
			result_learning_materials_data_name.map(o => o.title = o['data_name'])
	  			this.result_learning_materials_data_name = result_learning_materials_data_name
		 	}
		});
	},
	/**
	 * 获取data_type
	 */
	get_learning_materials_data_type(){
		this.$get("~/api/learning_materials/get_list?like=0", { page: 1, size: 10, "data_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_learning_materials_data_type = json.result.list;
			result_learning_materials_data_type.map(o => o.title = o['data_type'])
	  			this.result_learning_materials_data_type = result_learning_materials_data_type
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
	this.get_forum();
		this.get_registered_user_user_name();
		this.get_instrument_classification_instrument_category();
		this.get_instruments_and_equipment_instrument_name();
		this.get_instruments_and_equipment_instrument_category();
		this.get_instruments_and_equipment_state();
		this.get_instrument_reservation_instrument_name();
		this.get_instrument_reservation_instrument_category();
		this.get_instrument_reservation_appointment_date();
		this.get_data_classification_data_type();
		this.get_learning_materials_data_name();
		this.get_learning_materials_data_type();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_forum();
	  this.get_registered_user_user_name();
	  this.get_instrument_classification_instrument_category();
	  this.get_instruments_and_equipment_instrument_name();
	  this.get_instruments_and_equipment_instrument_category();
	  this.get_instruments_and_equipment_state();
	  this.get_instrument_reservation_instrument_name();
	  this.get_instrument_reservation_instrument_category();
	  this.get_instrument_reservation_appointment_date();
	  this.get_data_classification_data_type();
	  this.get_learning_materials_data_name();
	  this.get_learning_materials_data_type();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
