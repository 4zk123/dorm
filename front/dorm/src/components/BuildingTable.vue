<template>
    <div>
       <el-table :data="tableData" border @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="id" label="id">
      </el-table-column>
      <el-table-column prop="name" label="姓名">
      </el-table-column>
      <el-table-column prop="building" label="栋">
      </el-table-column>
      <el-table-column prop="room" label="寝室">
      </el-table-column>
      <el-table-column prop="bunk" label="床位" >
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="success" @click="handleEdit(scope.row)" style="width: 80px;">编辑 <i class="el-icon-edit"></i></el-button>

          <el-popconfirm
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="delById(scope.row.id)"
          >
            <el-button type="danger" slot="reference" style="margin-left: 5px; width: 80px;">删除 <i class="el-icon-remove"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[5, 10]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total=total>
    </el-pagination>
    </div>
</template>
<script>
export default {
    name:'buildingTable',
    props:[
        'tableData',
        'pageNum',
        'pageSize',
        'total',
        'log'
    ],
    methods: {
        handleSelectionChange(val){
        console.log(val)
      },
      handleEdit(row){
        console.log(row)
      },
      delById(id){
        console.log(id)
      },
      handleSizeChange(size){
        console.log(size)
        this.$emit('log')
      },
      handleCurrentChange(page){
        console.log(page)
      },
    }
}
</script>