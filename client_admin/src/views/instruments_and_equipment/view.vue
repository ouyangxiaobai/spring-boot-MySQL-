<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','instrument_no') || $check_field('add','instrument_no') || $check_field('set','instrument_no')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="仪器编号" prop="instrument_no">
					<el-input id="instrument_no" v-model="form['instrument_no']" placeholder="请输入仪器编号"
							  v-if="user_group === '管理员' || (form['instruments_and_equipment_id'] && $check_field('set','instrument_no')) || (!form['instruments_and_equipment_id'] && $check_field('add','instrument_no'))" :disabled="disabledObj['instrument_no_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','instrument_no')">{{form['instrument_no']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','instrument_name') || $check_field('add','instrument_name') || $check_field('set','instrument_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="仪器名称" prop="instrument_name">
					<el-input id="instrument_name" v-model="form['instrument_name']" placeholder="请输入仪器名称"
							  v-if="user_group === '管理员' || (form['instruments_and_equipment_id'] && $check_field('set','instrument_name')) || (!form['instruments_and_equipment_id'] && $check_field('add','instrument_name'))" :disabled="disabledObj['instrument_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','instrument_name')">{{form['instrument_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','instrument_category') || $check_field('add','instrument_category') || $check_field('set','instrument_category')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="仪器类别" prop="instrument_category">
					<el-select id="instrument_category" v-model="form['instrument_category']"
						v-if="user_group === '管理员' || (form['instruments_and_equipment_id'] && $check_field('set','instrument_category')) || (!form['instruments_and_equipment_id'] && $check_field('add','instrument_category'))">
						<el-option v-for="o in list_instrument_category" :key="o['instrument_category']" :label="o['instrument_category']"
							:value="o['instrument_category']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','instrument_category')">{{form['instrument_category']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','purchase_date') || $check_field('add','purchase_date') || $check_field('set','purchase_date')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="购入日期" prop="purchase_date">
					<el-date-picker :disabled="disabledObj['purchase_date_isDisabled']" v-if="user_group === '管理员' || (form['instruments_and_equipment_id'] && $check_field('set','purchase_date')) || (!form['instruments_and_equipment_id'] && $check_field('add','purchase_date'))" id="purchase_date"
						v-model="form['purchase_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','purchase_date')">{{form['purchase_date']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','photo') || $check_field('add','photo') || $check_field('set','photo')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="照片" prop="photo">
					<el-upload :disabled="disabledObj['photo_isDisabled']" id="photo" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_photo"
						:show-file-list="false" v-if="user_group === '管理员' || (form['instruments_and_equipment_id'] && $check_field('set','photo')) || (!form['instruments_and_equipment_id'] && $check_field('add','photo'))">
						<img v-if="form['photo']" :src="$fullUrl(form['photo'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','photo')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['photo'])" :preview-src-list="[$fullUrl(form['photo'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','state') || $check_field('add','state') || $check_field('set','state')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="状态" prop="state">
					<el-select id="state" v-model="form['state']"
						v-if="user_group === '管理员' || (form['instruments_and_equipment_id'] && $check_field('set','state')) || (!form['instruments_and_equipment_id'] && $check_field('add','state'))">
						<el-option v-for="o in list_state" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','state')">{{form['state']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','matters_needing_attention') || $check_field('add','matters_needing_attention') || $check_field('set','matters_needing_attention')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="注意事项" prop="matters_needing_attention">
					<el-input type="textarea" id="matters_needing_attention" v-model="form['matters_needing_attention']" placeholder="请输入注意事项"
						v-if="user_group === '管理员' || (form['instruments_and_equipment_id'] && $check_field('set','matters_needing_attention')) || (!form['instruments_and_equipment_id'] && $check_field('add','matters_needing_attention'))" :disabled="disabledObj['matters_needing_attention_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','matters_needing_attention')">{{form['matters_needing_attention']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','instrument_introduction') || $check_field('add','instrument_introduction') || $check_field('set','instrument_introduction')" :xs="24" :sm="24" :lg="24">
				<el-form-item label="仪器介绍" prop="instrument_introduction">
					<quill-editor v-model.number="form['instrument_introduction']"
						v-if="user_group === '管理员' || (form['instruments_and_equipment_id'] && $check_field('set','instrument_introduction')) || (!form['instruments_and_equipment_id'] && $check_field('add','instrument_introduction')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','instrument_introduction')" v-html="form['instrument_introduction']"></div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "instruments_and_equipment_id",
				url_add: "~/api/instruments_and_equipment/add?",
				url_set: "~/api/instruments_and_equipment/set?",
				url_get_obj: "~/api/instruments_and_equipment/get_obj?",
				url_upload: "~/api/instruments_and_equipment/upload?",

				query: {
					"instruments_and_equipment_id": 0,
				},

				form: {
					"instrument_no":'', // 仪器编号
					"instrument_name":'', // 仪器名称
					"instrument_category":'', // 仪器类别
					"purchase_date":'', // 购入日期
					"photo":'', // 照片
					"state":'', // 状态
					"matters_needing_attention":'', // 注意事项
					"instrument_introduction":'', // 仪器介绍
					"instruments_and_equipment_id": 0, // ID

				},
				disabledObj:{
					"instrument_no_isDisabled": false,
					"instrument_name_isDisabled": false,
					"instrument_category_isDisabled": false,
					"purchase_date_isDisabled": false,
					"photo_isDisabled": false,
					"state_isDisabled": false,
					"matters_needing_attention_isDisabled": false,
					"instrument_introduction_isDisabled": false,
				},
				//仪器类别选项列表
				list_instrument_category: [],
				//状态选项列表
				list_state: ['可预约','不可预约'],

			}
		},
		methods: {
			/**
			 * 获取仪器类别列表
			 */
			async get_list_instrument_category() {
				var json = await this.$get("~/api/instrument_classification/get_list?");
				if(json.result && json.result.list){
					this.list_instrument_category = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			/**
			 * 上传照片
			 * @param {Object} param图片参数
			 */
			upload_photo(param){
				this.uploadFile(param.file, "photo");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							if(dbKey === "charging_standard"){
								this.form['charging_rules'] = form[dbKey];
								this.disabledObj['charging_rules_isDisabled'] = true;
							};
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				this.form["purchase_date"] = this.$toTime(parseInt(this.form["purchase_date"]),"yyyy-MM-dd")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["purchase_date"]) > 9999){
					this.form["purchase_date"] = this.$toTime(parseInt(this.form["purchase_date"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/instruments_and_equipment/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/instruments_and_equipment/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/instruments_and_equipment/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/instruments_and_equipment/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/instruments_and_equipment/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_instrument_category();
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
