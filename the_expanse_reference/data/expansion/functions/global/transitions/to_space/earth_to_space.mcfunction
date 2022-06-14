#function expansion:vehicles/spaceship/transitions/transition_debug_step_1
execute if entity @s[scores={exp_x=0..,exp_z=0..}] in expansion:space run tp @s 120 512 120 -45 0
execute if entity @s[scores={exp_x=0..,exp_z=..0}] in expansion:space run tp @s 120 512 -120 -135 0
execute if entity @s[scores={exp_x=..0,exp_z=0..}] in expansion:space run tp @s -120 512 120 45 0
execute if entity @s[scores={exp_x=..0,exp_z=..0}] in expansion:space run tp @s -120 512 -120 135 0

