// pages/search/tang/tang.js

//获取应用实例
const app = getApp()

Page({
  /**
   * 页面的初始数据
   */
  data: {
    counter: 0,
    title: '',
    content: '',
    poetryArray: [],
    searchText: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.searchText = options.searchText
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    var searchText = this.data.searchText;
    app.showGlobalToast()
    wx.request({
      url: app.globalData.basicUrl + '/tang/search',
      data: {
        counter: 0,
        searchText: searchText
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: response => {
        app.hideGlobalToast()
        var data = response.data
        if (response.statusCode == 200) {
          this.setData({
            poetryArray: data
          })
        }
      }

    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () { },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () { },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    this.data.counter = 0
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () { },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    var counter = this.data.counter++
    var searchText = this.data.searchText
    app.showGlobalToast()
    wx.request({
      url: app.globalData.basicUrl + '/tang/search',
      data: {
        searchText: searchText,
        counter: counter
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: response => {
        var data = response.data
        if (response.statusCode == 200) {
          app.hideGlobalToast()
          this.setData({
            poetryArray: this.data.poetryArray.concat(data)
          })
        }
      }
    })
  }
})