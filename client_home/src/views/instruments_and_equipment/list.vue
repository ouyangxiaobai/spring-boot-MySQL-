<template>
	<div class="diy_list page_instruments_and_equipment" id="instruments_and_equipment_list">
		<div class="warp">
			<div class="container diy_list_container">
				<div class="diy_list_title">
					<div class="col">
						<span class="title">仪器设备列表</span>
					</div>
				</div>
				<div class="row diy_list_search">
					<div class="col">
						<!-- 搜索栏 -->
						<div class="view">							
							<span class="diy_list_search_title">关键字搜索：</span>
							<b-form-input size="sm" class="mr-sm-2" placeholder="仪器名称搜索" v-model="query['instrument_name']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="仪器类别搜索" v-model="query['instrument_category']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="状态搜索" v-model="query['state']" />
							<b-button size="sm" @click="search()" >
								<b-icon icon="search"/>
							</b-button>
						</div>
						<!-- /搜索栏 -->
					</div>
				</div>
				<div class="diy_list_select_box">
					<span class="diy_list_select_title">下拉搜索：</span>
						<div class="diy_list_dropdown_box">
						<div class="col">
							<!-- 筛选 -->
							<div class="view">
								<b-dropdown text="仪器类别" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','instrument_category')">全部</b-dropdown-item>
										<b-dropdown-item v-for="(o, i) in list_instrument_category" :key="i" @click="filter_set(o['instrument_category'],'instrument_category')" >
												{{ o['instrument_category'] }}
										</b-dropdown-item>
								</b-dropdown>
								<b-dropdown :text="title" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','state')">全部</b-dropdown-item>
										<b-dropdown-item v-for="(o,i) in list_state" @click="filter_set(o,'state')" >
										{{ o }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!-- /筛选 -->
						</div>
					</div>
					<div class="diy_list_sort_box">
						<div class="col">
							<!-- 排序 -->
							<div class="view">
								<b-dropdown text="排序" variant="outline-dark" left>
										<b-dropdown-item v-for="(o, i) in list_sort" :key="i" @click="set_sort(o)" >
												{{ o.name }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!--/排序 -->
						</div>
					</div>
				</div>
				<div class="row diy_list_box">
					<div class="col">
						<!-- 列表 -->
						<list_instruments_and_equipment :list="list" />
						<!-- /列表 -->
					</div>
				</div>
				<div class="row diy_list_page_box">
					<div class="col overflow-auto flex_cc">
						<!-- 分页器 -->
						<diy_pager v-model="query['page']" :size="query['size']" :count="count" v-on:toPage="toPage" v-on:toSize="toSize" ></diy_pager>
						<!-- /分页器 -->
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import list_instruments_and_equipment from "@/components/diy/list_instruments_and_equipment.vue";
	import diy_pager from "@/components/diy/diy_pager";
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		components: {
			diy_pager,
			list_instruments_and_equipment
		},
		data() {
			return {
				url_get_list: "~/api/instruments_and_equipment/get_list?like=0",

				// 查询条件
				query: {
					keyword: "",
					page: 1,
					size: 10,
					"instrument_name": "", // 仪器名称
					"instrument_category": "", // 仪器类别
					"state": "", // 状态
				},

				// 排序内容
				list_sort: [{
						name: "创建时间从高到低",
						value: "`create_time` desc",
					},
					{
						name: "创建时间从低到高",
						value: "`create_time` asc",
					},
					{
						name: "更新时间从高到低",
						value: "`update_time` desc",
					},
					{
						name: "更新时间从低到高",
						value: "`update_time` asc",
					},
					{
						name: "仪器名称正序",
						value: "`instrument_name` asc",
					},
					{
						name: "仪器名称倒序",
						value: "`instrument_name` desc",
					},
					{
						name: "仪器类别正序",
						value: "`instrument_category` asc",
					},
					{
						name: "仪器类别倒序",
						value: "`instrument_category` desc",
					},
					{
						name: "状态正序",
						value: "`state` asc",
					},
					{
						name: "状态倒序",
						value: "`state` desc",
					},
				],
				// 仪器类别列表
				"list_instrument_category": [],
				// 状态列表
				"list_state": ['可预约','不可预约'],
			}
		},
		methods: {
			/**
			 * 筛选选择
			 */
			filter_set(o,key) {
			    if (o == "全部") {
			        this.query[key] = "";
			    } else {
			        this.query[key] = o;
			    }
			    this.search();
			},

			/**
			 * 排序
			 */
			set_sort(o) {
			    this.query.orderby = o.value;
			    this.search();
			},
			/**
			 * 获取仪器类别列表
			 */
			async get_list_instrument_category() {
				var json = await this.$get("~/api/instrument_classification/get_list?");
				if (json.result) {
					this.list_instrument_category = json.result.list;
				} else if (json.error) {
					console.log(json.error);
				}
			},

			/**
			 * 筛选
			 */
			filter_instrument_category(o) {
				if (o == "全部") {
					this.query["instrument_category"] = "";
				} else {
					this.query["instrument_category"] = o;
				}
				this.search();
			},
			filter_state(o) {
				if (o == "全部") {
					this.query["state"] = "";
				} else {
					this.query["state"] = o;
				}
				this.search();
			},

			/**
			 * 重置
			 */
			reset() {
				this.query.instrument_name = ""
				this.query.instrument_category = ""
				this.query.state = ""
				this.search();
			},

			// 返回条数
			toSize(i){
				this.query.size = i;
				this.first();
			},

			// 返回页数
			toPage(i){
				this.query.page = i;
				this.first();
			},
		},
		computed: {
		},
		created() {
			/**
			 * 获取仪器类别列表
			 */
			this.get_list_instrument_category();
		}
	}
</script>

<style>
</style>
