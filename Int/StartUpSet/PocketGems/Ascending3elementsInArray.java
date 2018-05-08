void findIndices(vector<int>& nums, int& a, int& b, int& c){
	int cache1[2] = {0}, cache2[2] = {0};
	int p1 = 0, p2 = 0;
	for(int i = 0; i < nums.size(); ++i){
		// found sol in cache2;
		if(p2 != 0 && nums[i] > cache2[1]){
			a = cache2[0];
			b = cache2[1];
			c = nums[i];
			return;
		}

		// compare nums[i] with cache1 elements
		if(p1 == 0){
			cache1[p1++] = nums[i];
		}else if(p1 == 1){
			if(nums[i] < cache1[0]) cache1[0] = nums[i];
			else if(nums[i] > cache1[0]){
				cache1[p1++] = nums[i];
			}
		}else if(p1 == 2){
			if(nums[i] < cache1[0]){
				// find a better candicates, cache2 must be the worst, replace cache2 with cache1, and assign cache1[0] = nums[i]
				cache2[0] = cache1[0];
				cache2[1] = cache1[1];
				p2 = 2;

				cache1[0] = nums[i];
				p1 = 1;
			}else if(nums[i] < cache1[1]){
				cache1[1] = nums[i];
			}else if(nums[i] > cache1[1]){
				a = cache1[0];
				b = cache1[1];
				c = nums[i];
				return;
			}
		}
	}
	cout << "Cannot find a pair" << endl;
}
