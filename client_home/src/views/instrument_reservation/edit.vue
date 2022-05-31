<template>
	<div class="diy_edit page_instrument_reservation" id="instrument_reservation_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row'>
					<div v-if="$check_field('set','instrument_no') || $check_field('add','instrument_no') || $check_field('get','instrument_no')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>仪器编号:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_instrument_no" v-model="form['instrument_no']" placeholder="请输入仪器编号" v-if="(form['instrument_no'] && $check_field('set','instrument_no')) || (!form['instrument_no'] && $check_field('add','instrument_no'))"  :disabled="disabledObj['instrument_no_isDisabled']"/>
							<span v-else-if="$check_field('get','instrument_no')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','instrument_name') || $check_field('add','instrument_name') || $check_field('get','instrument_name')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>仪器名称:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_instrument_name" v-model="form['instrument_name']" placeholder="请输入仪器名称" v-if="(form['instrument_name'] && $check_field('set','instrument_name')) || (!form['instrument_name'] && $check_field('add','instrument_name'))"  :disabled="disabledObj['instrument_name_isDisabled']"/>
							<span v-else-if="$check_field('get','instrument_name')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','instrument_category') || $check_field('add','instrument_category') || $check_field('get','instrument_category')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>仪器类别:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_instrument_category" v-model="form['instrument_category']" placeholder="请输入仪器类别" v-if="(form['instrument_category'] && $check_field('set','instrument_category')) || (!form['instrument_category'] && $check_field('add','instrument_category'))"  :disabled="disabledObj['instrument_category_isDisabled']"/>
							<span v-else-if="$check_field('get','instrument_category')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','user_name') || $check_field('add','user_name') || $check_field('get','user_name')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>用户名:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_user_name" :disabled="disabledObj['user_name_isDisabled']" v-model="form['user_name']" v-if="(form['user_name'] && $check_field('set','user_name')) || (!form['user_name'] && $check_field('add','user_name'))" >
								<option v-for="o in list_user_user_name" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','user_name')">{{ form['user_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','contact_number') || $check_field('add','contact_number') || $check_field('get','contact_number')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>联系电话:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_contact_number" v-model="form['contact_number']" placeholder="请输入联系电话" v-if="(form['contact_number'] && $check_field('set','contact_number')) || (!form['contact_number'] && $check_field('add','contact_number'))"  :disabled="disabledObj['contact_number_isDisabled']"/>
							<span v-else-if="$check_field('get','contact_number')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','appointment_date') || $check_field('add','appointment_date') || $check_field('get','appointment_date')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>预约使用日期:
							</span>
						</div>
						<!-- 日长 -->
						<div class="diy_field diy_datetime">
							<input type="datetime-local" :disabled="disabledObj['appointment_date_isDisabled']" id="form_appointment_date" v-model="form['appointment_date']" placeholder="请输入预约使用日期" v-if="(form['appointment_date'] && $check_field('set','appointment_date')) || (!form['appointment_date'] && $check_field('add','appointment_date'))" />
							<span v-else-if="$check_field('get','appointment_date')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','reservation_purpose') || $check_field('add','reservation_purpose') || $check_field('get','reservation_purpose')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>预约用途:
							</span>
						</div>
						<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_reservation_purpose" v-model="form['reservation_purpose']" v-if="(form['reservation_purpose'] && $check_field('set','reservation_purpose')) || (!form['reservation_purpose'] && $check_field('add','reservation_purpose'))" :disabled="disabledObj['reservation_purpose_isDisabled']" />
							<span v-else-if="$check_field('get','reservation_purpose')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="user_group === '管理员' || $check_examine()" class="col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核状态:
							</span>
						</div>
						<div class="diy_field diy_select" v-if="$check_action('/instrument_reservation/edit','examine')">
							<!--<span> {{ form['examine_state'] }} </span>-->
							<select v-model="form['examine_state']">
								<option value="未审核">
									未审核
								</option>
								<option value="已通过">
									已通过
								</option>
								<option value="未通过">
									未通过
								</option>
							</select>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_state'] }}
							</span>
						</div>
					</div>
					<div v-if="user_group === '管理员' || $check_examine()" class="col-12 col-md-6" >
						<div class="diy_title">
							<span>
								审核回复:
							</span>
						</div>
						<div class="diy_field diy_desc" v-if="$check_action('/instrument_reservation/edit','examine')">
							<textarea v-model="form['examine_reply']"></textarea>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_reply'] }}
							</span>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/instrument_reservation/get_obj?",
				url_add: "~/api/instrument_reservation/add?",
				url_set: "~/api/instrument_reservation/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"instrument_no": "",
					"instrument_name": "",
					"instrument_category": "",
					"user_name": 0,
					"contact_number": "",
					"appointment_date": "",
					"reservation_purpose": "",
					"instrument_reservation_id": 0,
				},

				obj: {
					"instrument_no":'', // 仪器编号
					"instrument_name":'', // 仪器名称
					"instrument_category":'', // 仪器类别
					"user_name": 0, // 用户名
					"contact_number":'', // 联系电话
					"appointment_date": "1970-01-01 00:00:00",
					"reservation_purpose":'', // 预约用途
					"examine_state": "未审核",
					"examine_reply": "",
					"instrument_reservation_id": 0,
				},

				// 表单字段
				form: {
					"instrument_no":'', // 仪器编号
					"instrument_name":'', // 仪器名称
					"instrument_category":'', // 仪器类别
					"user_name": 0, // 用户名
					"contact_number":'', // 联系电话
					"appointment_date": "1970-01-01 00:00:00",
					"reservation_purpose":'', // 预约用途
					"examine_state": "未审核",
					"examine_reply": "",
					"instrument_reservation_id": 0,

				},
				disabledObj:{
					"instrument_no_isDisabled": false,
					"instrument_name_isDisabled": false,
					"instrument_category_isDisabled": false,
					"user_name_isDisabled": false,
					"contact_number_isDisabled": false,
					"appointment_date_isDisabled": false,
					"reservation_purpose_isDisabled": false,
				},
				// 用户列表
				list_user_user_name: [],

				// ID字段
				field: "instrument_reservation_id",
			}
		},
		methods: {
			/**
			 * 获取注册用户用户列表
			 */
			async get_list_user_user_name() {
				// if(this.user_group !== "管理员" && this.form["user_name"] === 0) {
				//     this.form["user_name"] = this.user.user_id;
				// }
				var json = await this.$get("~/api/user/get_list?user_group=注册用户");
				if(json.result && json.result.list){
					this.list_user_user_name = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			async get_user_session_user_name(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=注册用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["user_name"] = user_id
								_this.disabledObj['user_name' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
									for (var j=0;j<arrForm.length;j++){
										if (arr[i]===arrForm[j]){
											if (arr[i]!=="user_name") {
												_this.form[arrForm[j]] = res.result.obj[arr[i]]
												_this.disabledObj[arrForm[j] + '_isDisabled'] = true
												break;
											}
										}
									}
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/instrument_reservation/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				if (form) {
					this.obj = $.push(this.obj ,form);
					this.form = $.push(this.form ,form);
				}
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				for (var i=0;i<arr.length;i++){
					this.disabledObj[arr[i] + '_isDisabled'] = true
				}
				this.form["appointment_date"] = this.$toTime(parseInt(this.form["appointment_date"]),"yyyy-MM-dd hh:mm:ss")
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				var form = $.db.get("form");
				var obj = Object.assign({} ,form ,this.obj);
				if (form) {
					this.obj = $.push(this.obj ,obj);
				}
				if (form) {
					this.form = $.push(this.form ,form);
				}
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_user_session_user_name();
			this.get_list_user_user_name();
		}
	}
</script>

<style>
</style>
