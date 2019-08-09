# BaseAdapter-master
Universal adapter(通用adapter)


Android 万能的Adapter for ListView,RecyclerView,GridView等



Getting Started

How to use
To get a Git project into your build:

Step 1. Add the JitPack repository to your build file 

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.longzwg:BaseAdapter-master:${latest_version}'
	}
    
    
Step 3. Use
                
    recyclerAdapter = object : CommonRecyclerAdapter<DataBean>(this) {
                    override fun convert(viewHolder: CommonRecyclerViewHolder, t: DataBean?, position: Int) {
                        viewHolder.setText(R.id.name, t?.name ?: "")
                        viewHolder.setText(R.id.content, t?.content ?: "")
                        viewHolder.setOnClickListener(R.id.name, object : View.OnClickListener {
                            override fun onClick(p0: View?) {
                                Toast.makeText(this@ListActivity, t?.name ?: "", Toast.LENGTH_SHORT).show()
                            }
                        })
                    }

                    override fun getLayoutRes(viewType: Int): Int {
                        return R.layout.item_lv
                    }
                }
                
        listAdapter = object : CommonListAdapter<DataBean>(this, R.layout.item_lv) {
                    override fun convert(viewHolder: CommonViewListHolder, t: DataBean?, position: Int) {
                        viewHolder.setText(R.id.name, t?.name ?: "")
                        viewHolder.setText(R.id.content, t?.content ?: "")
                        viewHolder.setOnClickListener(R.id.name,
                            View.OnClickListener {
                                Toast.makeText(this@ListActivity, t?.name ?: "", Toast.LENGTH_SHORT).show()
                            })
                    }
                }
                
可以通过holder.getView(id)拿到任何控件。
ViewHolder中封装了大量的常用的方法，比如holder.setText(id,text)，holder.setOnClickListener(id,listener),holder.setVisible(id,true)等，可以支持使用。
