<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','data_no') || $check_field('add','data_no') || $check_field('set','data_no')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="资料编号" prop="data_no">
					<el-input id="data_no" v-model="form['data_no']" placeholder="请输入资料编号"
							  v-if="user_group === '管理员' || (form['learning_materials_id'] && $check_field('set','data_no')) || (!form['learning_materials_id'] && $check_field('add','data_no'))" :disabled="disabledObj['data_no_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','data_no')">{{form['data_no']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','data_name') || $check_field('add','data_name') || $check_field('set','data_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="资料名称" prop="data_name">
					<el-input id="data_name" v-model="form['data_name']" placeholder="请输入资料名称"
							  v-if="user_group === '管理员' || (form['learning_materials_id'] && $check_field('set','data_name')) || (!form['learning_materials_id'] && $check_field('add','data_name'))" :disabled="disabledObj['data_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','data_name')">{{form['data_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','data_type') || $check_field('add','data_type') || $check_field('set','data_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="资料类型" prop="data_type">
					<el-select id="data_type" v-model="form['data_type']"
						v-if="user_group === '管理员' || (form['learning_materials_id'] && $check_field('set','data_type')) || (!form['learning_materials_id'] && $check_field('add','data_type'))">
						<el-option v-for="o in list_data_type" :key="o['data_type']" :label="o['data_type']"
							:value="o['data_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','data_type')">{{form['data_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cover') || $check_field('add','cover') || $check_field('set','cover')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="封面" prop="cover">
					<el-upload :disabled="disabledObj['cover_isDisabled']" id="cover" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover"
						:show-file-list="false" v-if="user_group === '管理员' || (form['learning_materials_id'] && $check_field('set','cover')) || (!form['learning_materials_id'] && $check_field('add','cover'))">
						<img v-if="form['cover']" :src="$fullUrl(form['cover'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','cover')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['cover'])" :preview-src-list="[$fullUrl(form['cover'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','data') || $check_field('add','data') || $check_field('set','data')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="资料" prop="data">
					<div v-if="disabledObj['data_isDisabled']">
						<div v-if="$check_field('get','data')">
							<el-button type="primary" @click="$download($fullUrl(form['data']),form['data'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
					<div v-else>
						<el-upload v-if="user_group === '管理员' || (form['learning_materials_id'] && $check_field('set','data')) || (!form['learning_materials_id'] && $check_field('add','data'))" class="upload-demo" drag
								   action="" style="max-width: 300px;width: 100%;" :http-request="upload_data" :limit="1" accept="">
							<i class="el-icon-upload"></i>
							<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
						</el-upload>
						<div v-else-if="$check_field('get','data')">
							<el-button type="primary" @click="$download($fullUrl(form['data']),form['data'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','learning_video') || $check_field('add','learning_video') || $check_field('set','learning_video')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学习视频" prop="learning_video">
					<el-upload v-if="user_group === '管理员' || (form['learning_materials_id'] && $check_field('set','learning_video')) || (!form['learning_materials_id'] && $check_field('add','learning_video'))" class="upload-demo" drag
						action="" style="max-width: 300px;width: 100%;" :http-request="upload_learning_video" :limit="1" accept="video/ogg,video/mp4,video/webm">
						<i class="el-icon-upload"></i>
						<div class="el-upload__text">将视频拖到此处，或<em>点击上传</em></div>
					</el-upload>
					<div v-else-if="$check_field('get','learning_video')">
						<el-button type="primary" @click="download(form['learning_video'])">下载<i
								class="el-icon-download el-icon--right"></i></el-button>
					</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','information_introduction') || $check_field('add','information_introduction') || $check_field('set','information_introduction')" :xs="24" :sm="24" :lg="24">
				<el-form-item label="资料介绍" prop="information_introduction">
					<quill-editor v-model.number="form['information_introduction']"
						v-if="user_group === '管理员' || (form['learning_materials_id'] && $check_field('set','information_introduction')) || (!form['learning_materials_id'] && $check_field('add','information_introduction')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','information_introduction')" v-html="form['information_introduction']"></div>
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
				field: "learning_materials_id",
				url_add: "~/api/learning_materials/add?",
				url_set: "~/api/learning_materials/set?",
				url_get_obj: "~/api/learning_materials/get_obj?",
				url_upload: "~/api/learning_materials/upload?",

				query: {
					"learning_materials_id": 0,
				},

				form: {
					"data_no":'', // 资料编号
					"data_name":'', // 资料名称
					"data_type":'', // 资料类型
					"cover":'', // 封面
					"data":'', // 资料
					"learning_video":'', // 学习视频
					"information_introduction":'', // 资料介绍
					"learning_materials_id": 0, // ID

				},
				disabledObj:{
					"data_no_isDisabled": false,
					"data_name_isDisabled": false,
					"data_type_isDisabled": false,
					"cover_isDisabled": false,
					"data_isDisabled": false,
					"learning_video_isDisabled": false,
					"information_introduction_isDisabled": false,
				},
				//资料类型选项列表
				list_data_type: [],

			}
		},
		methods: {
			/**
			 * 获取资料类型列表
			 */
			async get_list_data_type() {
				var json = await this.$get("~/api/data_classification/get_list?");
				if(json.result && json.result.list){
					this.list_data_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			/**
			 * 上传封面
			 * @param {Object} param图片参数
			 */
			upload_cover(param){
				this.uploadFile(param.file, "cover");
			},
			/**
			 * 上传资料
			 * @param {Object} param文件参数
			 */
			upload_data(param){
				this.uploadFile(param.file, "data");
			},
			/**
			 * 上传学习视频
			 * @param {Object} param视频参数
			 */
			upload_learning_video(param){
				this.uploadFile(param.file, "learning_video");
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
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/learning_materials/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/learning_materials/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/learning_materials/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/learning_materials/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/learning_materials/view','get');
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
			this.get_list_data_type();
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
