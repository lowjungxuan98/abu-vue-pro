import request from '@/config/axios'

// 用户组 VO
export interface GroupVO {
  id: number // 编号
  name: string // 名字
  description: string // 描述
  status: number // 状态
}

// 用户组 API
export const GroupApi = {
  // 查询用户组分页
  getGroupPage: async (params: any) => {
    return await request.get({ url: `/system/group/page`, params })
  },

  // 查询用户组详情
  getGroup: async (id: number) => {
    return await request.get({ url: `/system/group/get?id=` + id })
  },

  // 新增用户组
  createGroup: async (data: GroupVO) => {
    return await request.post({ url: `/system/group/create`, data })
  },

  // 修改用户组
  updateGroup: async (data: GroupVO) => {
    return await request.put({ url: `/system/group/update`, data })
  },

  // 删除用户组
  deleteGroup: async (id: number) => {
    return await request.delete({ url: `/system/group/delete?id=` + id })
  },

  // 导出用户组 Excel
  exportGroup: async (params) => {
    return await request.download({ url: `/system/group/export-excel`, params })
  }
}