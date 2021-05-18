<template>
    <b-card no-body>
        <b-card-header class="border-0">
            <h3 class="mb-0">봉사활동</h3>
        </b-card-header>

        <el-table class="table-responsive table"
                  header-row-class-name="thead-light"
                  :data="VolunteerProjects">
            

            <el-table-column label="번호"
                             prop="budget"
                             min-width="140px">
            </el-table-column>

            <el-table-column label="담당자부서 연락처"
                             prop="budget"
                             min-width="140px">
            </el-table-column>

            <el-table-column label="행사명"
                             min-width="170px"
                             prop="status">
                <template v-slot="{row}">
                    <badge class="badge-dot mr-4" type="">
                        <i :class="`bg-${row.statusType}`"></i>
                        <span class="status" :class="`text-${row.statusType}`">{{row.status}}</span>
                    </badge>
                </template>
            </el-table-column>

            <el-table-column label="접수기간"
                             prop="budget"
                             min-width="140px">
            </el-table-column>

            <el-table-column label="행사기간"
                             prop="budget"
                             min-width="140px">
            </el-table-column>

            <el-table-column label="접수상태"
                             prop="budget"
                             min-width="140px">
            </el-table-column>

            <el-table-column label="진행상태"
                             prop="budget"
                             min-width="140px">
            </el-table-column>

            <el-table-column label="조회수"
                             prop="budget"
                             min-width="140px">
            </el-table-column>

            <el-table-column label="신청여부"
                             prop="budget"
                             min-width="140px">
            </el-table-column>

            
            
        </el-table>

        <b-card-footer class="py-4 d-flex justify-content-end">
            <base-pagination v-model="currentPage" :per-page="10" :total="50"></base-pagination>
        </b-card-footer>
    </b-card>
</template>
<script>
  import VolunteerProjects from './VolunteerProjects'
  import { Table, TableColumn} from 'element-ui'
  export default {
    name: 'Volunteer-table',
    components: {
      [Table.name]: Table,
      [TableColumn.name]: TableColumn
    },
    data() {
      return {
        VolunteerProjects,
        currentPage: 1
      };
    }
  }
</script>
