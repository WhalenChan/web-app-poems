// pages/poetry/poetry.js

const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    poetry_id: '',
    author_id: '',
    poetry: {}
  },

  onLoad: function(options) {

    this.setData({
      poetry_id: options.poetry_id,
      author_id: options.author_id
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
        poetry_id: this.data.poetry_id,
        author_id: this.data.author_id
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: response => {
        var data = response.data
        console.log(response)
        if (response.statusCode == 200) {
          app.hideGlobalToast()
          this.setData({
            poetry: data[0]
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