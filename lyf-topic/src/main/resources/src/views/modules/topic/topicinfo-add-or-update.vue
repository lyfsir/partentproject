<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="话题内容" prop="content">
      <el-input v-model="dataForm.content" placeholder="话题内容"></el-input>
    </el-form-item>
    <el-form-item label="话题状态(0显示，1不显示)" prop="status">
      <el-input v-model="dataForm.status" placeholder="话题状态(0显示，1不显示)"></el-input>
    </el-form-item>
    <el-form-item label="发表时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="发表时间"></el-input>
    </el-form-item>
    <el-form-item label="用户id" prop="uId">
      <el-input v-model="dataForm.uId" placeholder="用户id"></el-input>
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
          content: '',
          status: '',
          createTime: '',
          uId: ''
        },
        dataRule: {
          content: [
            { required: true, message: '话题内容不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '话题状态(0显示，1不显示)不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '发表时间不能为空', trigger: 'blur' }
          ],
          uId: [
            { required: true, message: '用户id不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/topic/topicinfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.content = data.topicInfo.content
                this.dataForm.status = data.topicInfo.status
                this.dataForm.createTime = data.topicInfo.createTime
                this.dataForm.uId = data.topicInfo.uId
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
              url: this.$http.adornUrl(`/topic/topicinfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'content': this.dataForm.content,
                'status': this.dataForm.status,
                'createTime': this.dataForm.createTime,
                'uId': this.dataForm.uId
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
