<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户名" prop="username">
      <el-input v-model="dataForm.username" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="dataForm.password" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item label="昵称" prop="nickname">
      <el-input v-model="dataForm.nickname" placeholder="昵称"></el-input>
    </el-form-item>
    <el-form-item label="手机号码" prop="mobile">
      <el-input v-model="dataForm.mobile" placeholder="手机号码"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
    </el-form-item>
    <el-form-item label="头像" prop="header">
      <el-input v-model="dataForm.header" placeholder="头像"></el-input>
    </el-form-item>
    <el-form-item label="性别" prop="gender">
      <el-input v-model="dataForm.gender" placeholder="性别"></el-input>
    </el-form-item>
    <el-form-item label="生日" prop="birth">
      <el-input v-model="dataForm.birth" placeholder="生日"></el-input>
    </el-form-item>
    <el-form-item label="所在城市" prop="city">
      <el-input v-model="dataForm.city" placeholder="所在城市"></el-input>
    </el-form-item>
    <el-form-item label="职业" prop="job">
      <el-input v-model="dataForm.job" placeholder="职业"></el-input>
    </el-form-item>
    <el-form-item label="个性签名" prop="sign">
      <el-input v-model="dataForm.sign" placeholder="个性签名"></el-input>
    </el-form-item>
    <el-form-item label="用户来源" prop="sourceType">
      <el-input v-model="dataForm.sourceType" placeholder="用户来源"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="注册时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="注册时间"></el-input>
    </el-form-item>
    <el-form-item label="社交uid" prop="socialUid">
      <el-input v-model="dataForm.socialUid" placeholder="社交uid"></el-input>
    </el-form-item>
    <el-form-item label="社交访问令牌" prop="accesstoken">
      <el-input v-model="dataForm.accesstoken" placeholder="社交访问令牌"></el-input>
    </el-form-item>
    <el-form-item label="社交令牌过期时间" prop="expiresIn">
      <el-input v-model="dataForm.expiresIn" placeholder="社交令牌过期时间"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          username: '',
          password: '',
          nickname: '',
          mobile: '',
          email: '',
          header: '',
          gender: '',
          birth: '',
          city: '',
          job: '',
          sign: '',
          sourceType: '',
          status: '',
          createTime: '',
          socialUid: '',
          accesstoken: '',
          expiresIn: ''
        },
        dataRule: {
          username: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          nickname: [
            { required: true, message: '昵称不能为空', trigger: 'blur' }
          ],
          mobile: [
            { required: true, message: '手机号码不能为空', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '邮箱不能为空', trigger: 'blur' }
          ],
          header: [
            { required: true, message: '头像不能为空', trigger: 'blur' }
          ],
          gender: [
            { required: true, message: '性别不能为空', trigger: 'blur' }
          ],
          birth: [
            { required: true, message: '生日不能为空', trigger: 'blur' }
          ],
          city: [
            { required: true, message: '所在城市不能为空', trigger: 'blur' }
          ],
          job: [
            { required: true, message: '职业不能为空', trigger: 'blur' }
          ],
          sign: [
            { required: true, message: '个性签名不能为空', trigger: 'blur' }
          ],
          sourceType: [
            { required: true, message: '用户来源不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '注册时间不能为空', trigger: 'blur' }
          ],
          socialUid: [
            { required: true, message: '社交uid不能为空', trigger: 'blur' }
          ],
          accesstoken: [
            { required: true, message: '社交访问令牌不能为空', trigger: 'blur' }
          ],
          expiresIn: [
            { required: true, message: '社交令牌过期时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/ums/umsmember/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.username = data.umsMember.username
                this.dataForm.password = data.umsMember.password
                this.dataForm.nickname = data.umsMember.nickname
                this.dataForm.mobile = data.umsMember.mobile
                this.dataForm.email = data.umsMember.email
                this.dataForm.header = data.umsMember.header
                this.dataForm.gender = data.umsMember.gender
                this.dataForm.birth = data.umsMember.birth
                this.dataForm.city = data.umsMember.city
                this.dataForm.job = data.umsMember.job
                this.dataForm.sign = data.umsMember.sign
                this.dataForm.sourceType = data.umsMember.sourceType
                this.dataForm.status = data.umsMember.status
                this.dataForm.createTime = data.umsMember.createTime
                this.dataForm.socialUid = data.umsMember.socialUid
                this.dataForm.accesstoken = data.umsMember.accesstoken
                this.dataForm.expiresIn = data.umsMember.expiresIn
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/ums/umsmember/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'username': this.dataForm.username,
                'password': this.dataForm.password,
                'nickname': this.dataForm.nickname,
                'mobile': this.dataForm.mobile,
                'email': this.dataForm.email,
                'header': this.dataForm.header,
                'gender': this.dataForm.gender,
                'birth': this.dataForm.birth,
                'city': this.dataForm.city,
                'job': this.dataForm.job,
                'sign': this.dataForm.sign,
                'sourceType': this.dataForm.sourceType,
                'status': this.dataForm.status,
                'createTime': this.dataForm.createTime,
                'socialUid': this.dataForm.socialUid,
                'accesstoken': this.dataForm.accesstoken,
                'expiresIn': this.dataForm.expiresIn
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
