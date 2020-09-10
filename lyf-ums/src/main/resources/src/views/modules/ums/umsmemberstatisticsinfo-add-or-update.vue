<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="会员id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="会员id"></el-input>
    </el-form-item>
    <el-form-item label="关注数量" prop="attendCount">
      <el-input v-model="dataForm.attendCount" placeholder="关注数量"></el-input>
    </el-form-item>
    <el-form-item label="粉丝数量" prop="fansCount">
      <el-input v-model="dataForm.fansCount" placeholder="粉丝数量"></el-input>
    </el-form-item>
    <el-form-item label="收藏食物的数量" prop="collectFoodCount">
      <el-input v-model="dataForm.collectFoodCount" placeholder="收藏食物的数量"></el-input>
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
          memberId: '',
          attendCount: '',
          fansCount: '',
          collectFoodCount: ''
        },
        dataRule: {
          memberId: [
            { required: true, message: '会员id不能为空', trigger: 'blur' }
          ],
          attendCount: [
            { required: true, message: '关注数量不能为空', trigger: 'blur' }
          ],
          fansCount: [
            { required: true, message: '粉丝数量不能为空', trigger: 'blur' }
          ],
          collectFoodCount: [
            { required: true, message: '收藏食物的数量不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/ums/umsmemberstatisticsinfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.memberId = data.umsMemberStatisticsInfo.memberId
                this.dataForm.attendCount = data.umsMemberStatisticsInfo.attendCount
                this.dataForm.fansCount = data.umsMemberStatisticsInfo.fansCount
                this.dataForm.collectFoodCount = data.umsMemberStatisticsInfo.collectFoodCount
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
              url: this.$http.adornUrl(`/ums/umsmemberstatisticsinfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'memberId': this.dataForm.memberId,
                'attendCount': this.dataForm.attendCount,
                'fansCount': this.dataForm.fansCount,
                'collectFoodCount': this.dataForm.collectFoodCount
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
