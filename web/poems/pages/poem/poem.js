// pages/poem/poem.js

const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    poemsId: '',
    authorId: '',
    poems: {}
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.setData({
      poemsId: options.poemsId,
      authorId: options.authorId
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {
    app.showGlobalToast()
    wx.request({
      url: app.globalData.basicUrl + '/song/listDetails',
      data: {
        poemsId: this.data.poemsId,
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
            poems: data
          })
        }
      }

    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  }

})