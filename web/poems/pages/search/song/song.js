// pages/search/song/song.js



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
    poemsArray: []
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
    var searchText = this.data.searchText
    app.showGlobalToast()
    wx.request({
      url: app.globalData.basicUrl + '/song/search',
      data: {
        counter: 0,
        searchText: searchText
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: response => { //箭头函数中的this 指向当前的page实例
        app.hideGlobalToast()
        var data = response.data
        if (response.statusCode == 200) {
          this.setData({
            poemsArray: data
          })
        }
      }

    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.data.counter = 0
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    this.data.counter = 0
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    this.data.counter = 0
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    var counter = this.data.counter++
    var searchText = this.data.searchText
    app.showGlobalToast()
    wx.request({
      url: app.globalData.basicUrl + '/song/search',
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
            poemsArray: this.data.poemsArray.concat(data)
          })
        }
      }
    })
  }
})