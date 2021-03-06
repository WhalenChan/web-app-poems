// pages/tang/tang.js

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
    inputShowed: false,
    inputVal: '',
    confirmInput: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    app.showGlobalToast()
    wx.request({
      url: app.globalData.basicUrl + '/tang/list',
      data: {
        counter: 0,
        searchText: ''
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
  onPullDownRefresh: function () { },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    var counter = this.data.counter++
    app.showGlobalToast()
    wx.request({
      url: app.globalData.basicUrl + '/tang/list',
      data: {
        searchText: '',
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
  },
  showInput: function () {
    this.setData({
      inputShowed: true
    });
  },
  hideInput: function () {
    this.setData({
      inputVal: "",
      inputShowed: false
    });
  },
  clearInput: function () {
    this.setData({
      inputVal: ""
    });
  },
  inputTyping: function (e) {
    this.setData({
      inputVal: e.detail.value
    });
  },
  inputConfirm: function (e) {
    var input = e.detail.value
    if (input) {
      this.setData({
        inputConfirm: true
      })
      wx.navigateTo({
        url: '../search/tang/tang?searchText='+input,
      })
    }
  }
})