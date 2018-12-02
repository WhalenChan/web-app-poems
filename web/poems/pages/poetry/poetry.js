// pages/poetry/poetry.js

const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    poetryId: '',
    authorId: '',
    poetry: {}
  },

  onLoad: function(options) {

    this.setData({
      poetryId: options.poetryId,
      authorId: options.authorId
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function (options) {
    app.showGlobalToast()
    wx.request({
      url: app.globalData.basicUrl + '/tang/listDetails',
      data: {
        poetryId: this.data.poetryId,
        authorId: this.data.authorId
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: response => {
        var data = response.data
        if (response.statusCode == 200) {
          app.hideGlobalToast()
          this.setData({
            poetry: data
          })
        }
      }
    })

  },
  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})